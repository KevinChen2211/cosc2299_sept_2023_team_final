import json
import boto3
from decimal import Decimal

dynamodb = boto3.resource('dynamodb')
table = dynamodb.Table('Customer')
from boto3.dynamodb.conditions import Attr

def lambda_handler(event, context):
    try:
        http_method = event['httpMethod']
        
        if http_method == 'GET':
            # Extract parameters from the query string
            if 'pathParameters' in event and event['pathParameters'] is not None:
                path_parameters = event['pathParameters']
                email = path_parameters.get('email')
                password = path_parameters.get('password')
                password = password.replace("%20", " ")
                
                response = table.get_item(
                    Key={'email': email}
                )
                
                if 'Item' in response:
                    stored_password = response['Item']['password']
                    if stored_password == password:
                        item = response['Item']
                        return {
                            'statusCode': 200,
                            'headers': {
                                'Content-Type': 'application/json'
                            },
                            'body': json.dumps(item)
                        }
                    else:
                        return {
                            'statusCode': 401,
                            'headers': {
                                    'Content-Type': 'text/plain'
                            },
                            'body': 'Incorrect password. Unable to return customer details.'
                        }
                else:
                    return {
                        'statusCode': 404,
                        'headers': {
                                'Content-Type': 'text/plain'
                        },
                        'body': 'No customer found.'
                    }
                    
            else:
                return {
                    'statusCode': 400,
                    'headers': {
                            'Content-Type': 'text/plain'
                    },
                    'body': 'Incorrect syntax provided.'
                }
        elif http_method == 'PUT':
            # Extract parameters from the query string
            if 'pathParameters' in event and event['pathParameters'] is not None and 'queryStringParameters' in event and event['queryStringParameters'] is not None:
                path_parameters = event['pathParameters']
                email = path_parameters.get('email')
                password = path_parameters.get('password')
                password = password.replace("%20", " ")
                
                query_parameters = event['queryStringParameters']
                firstName = query_parameters.get('firstName', '')
                print(firstName)
                lastName = query_parameters.get('lastName', '')
                print(lastName)
                address = query_parameters.get('address', '')
                print(address)
                phone = query_parameters.get('phone', '')
                print(phone)
                new_password = query_parameters.get('password', '')
                print(new_password)
                
                response = table.get_item(
                    Key={'email': email}
                )
            
                # Check if the customer exists and verify the supplied password
                if 'Item' in response:
                    stored_password = response['Item']['password']
                    if stored_password == password:
                        # Create a dictionary to collect the attributes to update
                        update_expression = "SET"
                        expression_attribute_values = {}
                        
                        if not (firstName or lastName or address or phone or new_password):
                            return {
                                'statusCode': 400,
                                'headers': {
                                        'Content-Type': 'text/plain'
                                },
                                'body': 'Incorrect syntax provided.'
                            }
                    
                        if firstName:
                            update_expression += " firstName = :firstName,"
                            expression_attribute_values[":firstName"] = firstName
                    
                        if lastName:
                            update_expression += " lastName = :lastName,"
                            expression_attribute_values[":lastName"] = lastName
                    
                        if address:
                            update_expression += " address = :address,"
                            expression_attribute_values[":address"] = address
                    
                        if phone:
                            update_expression += " phone = :phone,"
                            expression_attribute_values[":phone"] = phone
                    
                        if new_password:
                            update_expression += " password = :password,"
                            expression_attribute_values[":password"] = new_password
                    
                        # Remove the trailing comma from the update expression
                        update_expression = update_expression.rstrip(',')
            
                        table.update_item(
                            Key={'email': email},
                            UpdateExpression=update_expression,
                            ExpressionAttributeValues=expression_attribute_values
                        )
                        
                        return {
                            'statusCode': 200,
                            'headers': {
                                    'Content-Type': 'text/plain'
                            },
                            'body': email + ' details updated successfully.'
                        }
                    else:
                        return {
                            'statusCode': 401,
                            'headers': {
                                    'Content-Type': 'text/plain'
                            },
                            'body': 'Incorrect password. Unable to update customer details.'
                        }
                else:
                    return {
                        'statusCode': 404,
                        'headers': {
                                'Content-Type': 'text/plain'
                        },
                        'body': 'No customer found.'
                    }
            else:
                return {
                    'statusCode': 400,
                    'headers': {
                            'Content-Type': 'text/plain'
                    },
                    'body': 'Incorrect syntax provided.'
                }
        elif http_method == 'POST':
            # Parse the request body containing customer attributes
            request_body = json.loads(event['body'])
            
            # Extract and validate required attributes
            required_attributes = ['email', 'firstName', 'lastName', 'password', 'address', 'phone']
            for attr in required_attributes:
                if attr not in request_body:
                    return {
                        'statusCode': 400,
                        'headers': {
                            'Content-Type': 'text/plain'
                        },
                        'body': f'Missing required attribute: {attr}'
                    }
            
            # Check if the customer with the same email already exists
            existing_customer = table.get_item(
                Key={'email': request_body['email']}
            )
            
            if 'Item' in existing_customer:
                return {
                    'statusCode': 409,  # Conflict
                    'headers': {
                        'Content-Type': 'text/plain'
                    },
                    'body': 'Customer with the same email already exists.'
                }
            
            # Create a new customer entry
            table.put_item(
                Item={
                    'email': request_body['email'],
                    'firstName': request_body['firstName'],
                    'lastName': request_body['lastName'],
                    'password': request_body['password'],
                    'address': request_body.get('address', ''),
                    'phone': request_body.get('phone', '')
                }
            )
            
            return {
                'statusCode': 201,  # Created
                'headers': {
                    'Content-Type': 'text/plain'
                },
                'body': 'Customer created successfully.'
            }
        elif http_method == 'DELETE':
            # Extract parameters from the path
            if 'pathParameters' in event and event['pathParameters'] is not None:
                path_parameters = event['pathParameters']
                email = path_parameters.get('email')
                password = path_parameters.get('password')
                password = password.replace("%20", " ")
        
                # Check if the customer with the provided email exists
                existing_customer = table.get_item(
                    Key={'email': email}
                )
        
                if 'Item' in existing_customer:
                    stored_password = existing_customer['Item']['password']
                    if stored_password == password:
                        # Delete the customer entry
                        table.delete_item(
                            Key={'email': email}
                        )
        
                        return {
                            'statusCode': 200,
                            'headers': {
                                'Content-Type': 'text/plain'
                            },
                            'body': 'Customer deleted successfully.'
                        }
                    else:
                        return {
                            'statusCode': 401,
                            'headers': {
                                'Content-Type': 'text/plain'
                            },
                            'body': 'Incorrect password. Unable to delete customer.'
                        }
                else:
                    return {
                        'statusCode': 404,
                        'headers': {
                            'Content-Type': 'text/plain'
                        },
                        'body': 'Customer not found.'
                    }
            else:
                return {
                    'statusCode': 400,
                    'headers': {
                        'Content-Type': 'text/plain'
                    },
                    'body': 'Incorrect syntax provided.'
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