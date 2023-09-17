import json
import boto3

dynamodb = boto3.resource('dynamodb')
table = dynamodb.Table('Product')

def lambda_handler(event, context):
    try:
        # Scan the DynamoDB table to get all items
        response = table.scan()

        # Extract the 'subcategory' values from the items and make them unique
        sub_categories = set(item['subcategory'] for item in response.get('Items', []))

        # Convert the set of unique subcategories to a list
        unique_sub_categories = list(sub_categories)

        # Return the unique subcategory values as a JSON response
        return {
            'statusCode': 200,
            'headers': {
                'Content-Type': 'application/json'
            },
            'body': json.dumps(unique_sub_categories)
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