import json
import boto3
from decimal import Decimal

dynamodb = boto3.resource('dynamodb')
table = dynamodb.Table('OpeningTime')

def lambda_handler(event, context):
    try:
        http_method = event['httpMethod']
        
        if http_method == 'GET':
            # Extract store name from path parameter
            if 'pathParameters' in event and event['pathParameters'] is not None:
                path_parameters = event['pathParameters']
                store_name = path_parameters.get('storeName')
                store_name = store_name.replace('%20', ' ')
                
                # Query opening times by store
                response = table.query(
                    KeyConditionExpression='storeName = :sn',
                    ExpressionAttributeValues={
                        ':sn': store_name
                    }
                )
                
                items = response.get('Items', [])
                
                # Convert Decimal objects to floats
                for item in items:
                    for key, value in item.items():
                        if isinstance(value, Decimal):
                            item[key] = float(value)
                
                if items:
                    return {
                        'statusCode': 200,
                        'headers': {
                            'Content-Type': 'application/json'
                        },
                        'body': json.dumps(items)
                    }
                else:
                    return {
                        'statusCode': 404,
                        'headers': {
                            'Content-Type': 'text/plain'
                        },
                        'body': 'No opening times found for the store.'
                    }
            else:
                return {
                    'statusCode': 400,
                    'headers': {
                        'Content-Type': 'text/plain'
                    },
                    'body': 'Store name not provided in the path.'
                }
        
        elif http_method == 'POST':
            # Parse the request body containing opening times
            request_body = json.loads(event['body'])
            
            # Validate and extract attributes
            required_attributes = ['storeName', 'dayOfWeek', 'from', 'to', 'isClosed']
            for attr in required_attributes:
                if attr not in request_body:
                    return {
                        'statusCode': 400,
                        'headers': {
                            'Content-Type': 'text/plain'
                        },
                        'body': f'Missing required attribute: {attr}'
                    }
            
            table.put_item(Item=request_body)
            
            return {
                'statusCode': 200,
                'headers': {
                    'Content-Type': 'text/plain'
                },
                'body': 'Opening time added or updated successfully.'
            }
        else:
            return {
                'statusCode': 405,
                'headers': {
                    'Content-Type': 'text/plain'
                },
                'body': 'Method Not Allowed'
            }
    except Exception as e:
        # Handle any errors and return an error response
        return {
            'statusCode': 500,
            'headers': {
                'Content-Type': 'text/plain'
            },
            'body': str(e)
        }
