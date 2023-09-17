import json
import boto3
from decimal import Decimal

dynamodb = boto3.resource('dynamodb')
table = dynamodb.Table('Product')
from boto3.dynamodb.conditions import Attr

def lambda_handler(event, context):
    try:
        http_method = event['httpMethod']
        
        if http_method == 'GET':
            # Extract category parameters from the query string
            noParams = False
            if 'pathParameters' in event and event['pathParameters'] is not None:
                path_parameters = event['pathParameters']
                product_id = path_parameters.get('id')
                response = table.scan(FilterExpression=Attr('productID').eq(product_id))
                items = response.get('Items', [])
            elif 'queryStringParameters' in event and event['queryStringParameters'] is not None:
                query_parameters = event['queryStringParameters']
                categories = query_parameters.get('cat', '').split(',')
                categories = [item for item in categories if item != '']
                print(categories)
            
                subcategories = query_parameters.get('subcat', '').split(',')
                subcategories = [item for item in subcategories if item != '']
                print(subcategories)
            
                names = query_parameters.get('name', '').split(',')
                names = [item.strip().lower() for item in names if item != '']
                print(names)
                
                search = query_parameters.get('search', '').strip().lower()
                print(search)
            
                ids = query_parameters.get('id', '').split(',')
                ids = [item for item in ids if item != '']
                print(ids)
            
                chains = query_parameters.get('chain', '').split(',')
                chains = [item for item in chains if item != '']
                print(chains)
            
                # Filter items in DynamoDB based on the specified categories
                filtered_items = []
        
                filter_expression = None
        
                # Conditionally add attribute checks if the attribute lists are not empty
                if categories:
                    filter_expression = Attr('category').is_in(categories)
                
                if subcategories:
                    if filter_expression:
                        filter_expression &= Attr('subcategory').is_in(subcategories)
                    else:
                        filter_expression = Attr('subcategory').is_in(subcategories)
                
                if names:
                    if filter_expression:
                        filter_expression &= Attr('lower_name').is_in(names)
                    else:
                        filter_expression = Attr('lower_name').is_in(names)
                
                if chains:
                    if filter_expression:
                        filter_expression &= Attr('chain').is_in(chains)
                    else:
                        filter_expression = Attr('chain').is_in(chains)
                
                if ids:
                    if filter_expression:
                        filter_expression &= Attr('productID').is_in(ids)
                    else:
                        filter_expression = Attr('productID').is_in(ids)
                        
                if search:
                    if filter_expression:
                        filter_expression &= Attr('lower_name').contains(search)
                    else:
                        filter_expression = Attr('lower_name').contains(search)
            
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
                # Perform a scan operation without a filter expression to return all products
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
        elif http_method == 'POST':
            # Parse the JSON body of the POST request
            try:
                request_body = json.loads(event['body'])
                productID = request_body.get('productID')
                name = request_body.get('name')
                chain = request_body.get('chain')
                avgRating = Decimal(str(request_body.get('avgRating')))
                category = request_body.get('cat')
                subcategory = request_body.get('subcat')
                imageLocation = request_body.get('imageLocation')
                price = Decimal(str(request_body.get('price')))
                quantity = Decimal(str(request_body.get('quantity')))
    
                # Perform validation of required attributes here if needed
                if not (productID and name and chain and avgRating and category and subcategory and imageLocation and price and quantity):
                    return {
                        'statusCode': 400,
                        'headers': {
                            'Content-Type': 'text/plain'
                        },
                        'body': 'Missing required attributes in the request body.'
                    }
    
                # Create a new item in DynamoDB with the provided attributes
                new_item = {
                    'productID': productID,
                    'name': name,
                    'lower_name': name.strip().lower(),
                    'chain': chain,
                    'avgRating': avgRating,
                    'category': category,
                    'subcategory': subcategory,
                    'imageLocation': imageLocation,
                    'price': price,
                    'quantity': quantity
                }
    
                # Insert the new item into the DynamoDB table
                table.put_item(Item=new_item)
    
                # Return a success response
                return {
                    'statusCode': 200,
                    'headers': {
                        'Content-Type': 'text/plain'
                    },
                    'body': 'Product added successfully.'
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
        elif http_method == 'DELETE':
            try:
                # Extract the productID from the URL path
                path_parameters = event['pathParameters']
                product_id = path_parameters.get('id')
    
                if not product_id:
                    return {
                        'statusCode': 400,
                        'headers': {
                            'Content-Type': 'text/plain'
                        },
                        'body': 'Product ID is missing in the URL path.'
                    }
    
                # Delete the product with the specified productID from DynamoDB
                table.delete_item(
                    Key={'productID': product_id}
                )
    
                # Return a success response
                return {
                    'statusCode': 200,
                    'headers': {
                        'Content-Type': 'text/plain'
                    },
                    'body': f'Product with ID {product_id} has been deleted.'
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
