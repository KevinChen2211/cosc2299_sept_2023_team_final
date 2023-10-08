import json
import requests
import boto3

# Initialize DynamoDB client
dynamodb = boto3.client('dynamodb', region_name='ap-southeast-2')
table_name = 'Product'

def lambda_handler(event, context):
    # Fetch products from the REST API
    aldi_url = "https://ibw8gto5v4.execute-api.ap-southeast-2.amazonaws.com/example/products"
    aldi_products = requests.get(aldi_url).json()
    
    coles_url = "https://ra4jzyi0r3.execute-api.ap-southeast-2.amazonaws.com/example/products"
    coles_products = requests.get(coles_url).json()
    
    woolies_url = "https://l83c4wphqd.execute-api.ap-southeast-2.amazonaws.com/example/products"
    woolies_products = requests.get(woolies_url).json()
    
    addProducts(aldi_products, 'Aldi')
    addProducts(coles_products, 'Coles')
    addProducts(woolies_products, 'Woolworths')
    
    print("Products updated in DynamoDB")

def addProducts(products_data, chain):
    print(products_data)
    for product in products_data:
        print(product)
        item = {
            "productID": {"S": product['productID']},
            "name": {"S": product['name']},
            "lower_name": {"S": product['lower_name']},
            "category": {"S": product['category']},
            "subcategory": {"S": product['subcategory']},
            "avgRating": {"N": str(product['avgRating'])},
            "chain": {"S": chain},
            "quantity": {"N": str(product['quantity'])},
            "price": {"N": str(product['price'])},
            "imageLocation": {"S": product['imageLocation']},
            "isPromoted": {"BOOL": product['isPromoted']}
        }
        
        # Update the DynamoDB item
        dynamodb.put_item(TableName=table_name, Item=item)