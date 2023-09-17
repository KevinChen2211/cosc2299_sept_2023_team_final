import json
import boto3
from decimal import Decimal

dynamodb = boto3.resource('dynamodb')
table = dynamodb.Table('Store')
from boto3.dynamodb.conditions import Attr

def lambda_handler(event, context):
    try:
        http_method = event['httpMethod']
        
        if http_method == 'GET':
            # Extract parameters from the query string
            noParams = False
            if 'pathParameters' in event and event['pathParameters'] is not None:
                path_parameters = event['pathParameters']
                name = path_parameters.get('name')
                name = name.replace("%20", " ")
                response = table.scan(FilterExpression=Attr('name').eq(name))
                items = response.get('Items', [])
                
            elif 'queryStringParameters' in event and event['queryStringParameters'] is not None:
                query_parameters = event['queryStringParameters']
                postcodes = query_parameters.get('postcode', '').split(',')
                postcodes = [item for item in postcodes if item != '']
                print(postcodes)
            
                chains = query_parameters.get('chain', '').split(',')
                chains = [item for item in chains if item != '']
                print(chains)
            
                # Filter items in DynamoDB based on the specified categories
                filtered_items = []
        
                filter_expression = None
                
                if postcodes:
                    filter_expression = Attr('postcode').is_in(postcodes)
                
                if chains:
                    if filter_expression:
                        filter_expression &= Attr('chain').is_in(chains)
                    else:
                        filter_expression = Attr('chain').is_in(chains)
            
                # Check if filter_expression is still None, meaning no attribute checks were added
                if filter_expression is None:
                    noParams = True
                else:
                    # Perform a scan operation with filter expression
                    response = table.scan(FilterExpression=filter_expression)
                    items = response.get('Items', [])
            else:
                noParams = True

            if noParams:
                # Perform a scan operation without a filter expression to return all stores
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