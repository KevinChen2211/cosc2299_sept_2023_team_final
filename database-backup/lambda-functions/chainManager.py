import json
import boto3
from decimal import Decimal

dynamodb = boto3.resource('dynamodb')
table = dynamodb.Table('Chain')
from boto3.dynamodb.conditions import Attr

def lambda_handler(event, context):
    try:
        http_method = event['httpMethod']
        
        if http_method == 'GET':
            if 'pathParameters' in event and event['pathParameters'] is not None:
                path_parameters = event['pathParameters']
                name = path_parameters.get('name')
                response = table.scan(FilterExpression=Attr('name').eq(name))
                items = response.get('Items', [])
            else:
                response = table.scan()
                items = response.get('Items', [])
        
            # Convert Decimal objects to floats
            for item in items:
                for key, value in item.items():
                    if isinstance(value, Decimal):
                        item[key] = float(value)
        
            # Return a response in the format expected by API Gateway
            if not items:
                return {
                    'statusCode': 404,
                    'headers': {
                            'Content-Type': 'text/plain'
                    },
                    'body': 'No chains found.'
                }
            else:
                return {
                    'statusCode': 200,
                    'headers': {
                        'Content-Type': 'application/json'
                    },
                    'body': json.dumps(items)
                }
        elif http_method == 'PUT':
            # Parse the query
            try:
                query_parameters = event['queryStringParameters']
                avgRating = query_parameters.get('setRating', '')
                print(avgRating)
                
                path_parameters = event['pathParameters']
                name = path_parameters.get('name')
                print(name)
                
                # Perform validation of required attributes here if needed
                if not (avgRating and name):
                    return {
                        'statusCode': 400,
                        'headers': {
                            'Content-Type': 'text/plain'
                        },
                        'body': 'Missing setRating or name attribute in query.'
                    }
    
                # Update the rating value in DynamoDB
                response = table.update_item(
                    Key={'name': name},
                    UpdateExpression='SET avgRating = :avgRating',
                    ExpressionAttributeValues={':avgRating': Decimal(avgRating)}
                )
    
                # Return a success response
                return {
                    'statusCode': 200,
                    'headers': {
                        'Content-Type': 'text/plain'
                    },
                    'body': 'Rating updated successfully.'
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
        else:
            # Return a 405 Method Not Allowed response for unsupported HTTP methods
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