import json
import boto3

dynamodb = boto3.resource('dynamodb')
table = dynamodb.Table('Product')

def lambda_handler(event, context):
    try:
        # Scan the DynamoDB table to get all items
        response = table.scan()

        # Extract the 'category' values from the items and make them unique
        categories = set(item['category'] for item in response.get('Items', []))

        # Convert the set of unique categories to a list
        unique_categories = list(categories)

        # Return the unique category values as a JSON response
        return {
            'statusCode': 200,
            'headers': {
                'Content-Type': 'application/json'
            },
            'body': json.dumps(unique_categories)
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