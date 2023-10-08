import json
import boto3
from decimal import Decimal

dynamodb = boto3.resource('dynamodb')
table = dynamodb.Table('Woolworths')
from boto3.dynamodb.conditions import Attr

def lambda_handler(event, context):
    try:
        http_method = event['httpMethod']
        
        if http_method == 'GET':
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
                    'body': 'No items found.'
                }
            else:
                return {
                    'statusCode': 200,
                    'headers': {
                        'Content-Type': 'application/json'
                    },
                    'body': json.dumps(items)
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