import boto3
from random import random, randint

dynamodb = boto3.client('dynamodb')

catList = ['fruit-and-veg','meat-and-seafood','dairy-and-eggs','bakery']

productList = [
    ('Apples 300g','fruit-and-veg','apples'),
    ('Apples Per Kg','fruit-and-veg','apples'),
    ('Bananas 400g','fruit-and-veg','bananas'),
    ('Potatos 1kg','fruit-and-veg','potatos'),
    ('Bananas Fresh','fruit-and-veg','bananas'),
    ('Potatos 2kg','fruit-and-veg','bananas'),
    ('Avocado Large','fruit-and-veg','avocados'),
    ('Avocado 4pk','fruit-and-veg','avocados'),
    ('Avocado x6','fruit-and-veg','avocados'),
    ('Apple Golden Delicious','fruit-and-veg','apples'),
    ('Grapes Red','fruit-and-veg','grapes'),
    ('Grapes Green 250g','fruit-and-veg','grapes'),
    ('Pineapple Large','fruit-and-veg','pineapples'),
    ('Pineapple x1','fruit-and-veg','pineapples'),
    ('Watermelon Half','fruit-and-veg','watermelon'),
    ('Full Watermelon','fruit-and-veg','watermelon'),
    ('Watermelon 600g','fruit-and-veg','watermelon'),
    ('Peaches 400g','fruit-and-veg','peaches'),
    ('650g Peaches','fruit-and-veg','peaches'),
    ('Eye Fillet 400g','meat-and-seafood','beef'),
    ('Eye Fillet 250g','meat-and-seafood','beef'),
    ('Australian Eye Fillet 350g','meat-and-seafood','beef'),
    ('Porterhouse 500g','meat-and-seafood','beef'),
    ('350g Porterhouse Steak','meat-and-seafood','beef'),
    ('Whole Chicken','meat-and-seafood','chicken'),
    ('Chicken Half','meat-and-seafood','chicken'),
    ('Chicken Breast 400g','meat-and-seafood','chicken'),
    ('Pork Ribs 500g','meat-and-seafood','pork'),
    ('Pork Spare Ribs 300g','meat-and-seafood','pork'),
    ('Pork Shoulder 1.4kg','meat-and-seafood','pork'),
    ('Pork Shoulder Roast 1kg','meat-and-seafood','pork'),
    ('Double Smoked Bacon 500g','meat-and-seafood','pork'),
    ('Short Cut Bacon 200g','meat-and-seafood','pork'),
    ('Bacon Smoked 200g','meat-and-seafood','pork'),
    ('Blue Grenadier Fillets Per Kg','meat-and-seafood','fish'),
    ('Blue Grenadier 300g','meat-and-seafood','fish'),
    ('Salmon 300g','meat-and-seafood','fish'),
    ('Salmon Fillet Per Kg','meat-and-seafood','fish'),
    ('Australian-Sourced Salmon 250g','meat-and-seafood','fish'),
    ('Tiger Prawns Per Kg','meat-and-seafood','fish'),
    ('Prawns 250g','meat-and-seafood','fish'),
    ('Barramundi Fillet Fresh Per Kg','meat-and-seafood','fish'),
    ('1L home brand milk', 'dairy-and-eggs', 'milk'),
    ('2L home brand milk', 'dairy-and-eggs', 'milk'),
    ('4L home brand milk', 'dairy-and-eggs', 'milk'),
    ('1L full cream milk', 'dairy-and-eggs', 'milk'),
    ('2L full cream milk', 'dairy-and-eggs', 'milk'),
    ('4L full cream milk', 'dairy-and-eggs', 'milk'),
    ('1L lite milk', 'dairy-and-eggs', 'milk'),
    ('2L lite milk', 'dairy-and-eggs', 'milk'),
    ('4L lite milk', 'dairy-and-eggs', 'milk'),
    ('Home brand tasty cheese', 'dairy-and-eggs', 'cheese'),
    ('Bega tasty cheese', 'dairy-and-eggs', 'cheese'),
    ('Coon tasty cheese', 'dairy-and-eggs', 'cheese'),
    ('Home brand cheddar cheese', 'dairy-and-eggs', 'cheese'),
    ('Bega cheddar cheese', 'dairy-and-eggs', 'cheese'),
    ('Coon cheddar cheese', 'dairy-and-eggs', 'cheese'),
    ('12 free range eggs', 'dairy-and-eggs', 'eggs'),
    ('12 cage range eggs', 'dairy-and-eggs', 'eggs'),
    ('6 caged eggs', 'dairy-and-eggs', 'eggs'),
    ('6 rspca approved eggs', 'dairy-and-eggs', 'eggs'),
    ('Loaf of multigrain bread', 'bakery', 'bread'),
    ('Loaf of white bread', 'bakery', 'bread'),
    ('Loaf of wholegrain bread', 'bakery', 'bread'),
    ('multigrain bread loaf', 'bakery', 'bread'),
    ('white bread loaf', 'bakery', 'bread'),
    ('wholegrain bread loaf', 'bakery', 'bread'),
    ('multigrain loaf bread', 'bakery', 'bread'),
    ('white loaf bread', 'bakery', 'bread'),
    ('wholegrain loaf bread', 'bakery', 'bread'),
    ('Pain au chocolate', 'bakery', 'croissant'),
    ('Chocolate croissant', 'bakery', 'croissant'),
    ('Butter croissant', 'bakery', 'croissant'),
    ('12 Dinner rolls', 'bakery', 'rolls'),
    ('6 Dinner rolls', 'bakery', 'rolls'),
    ('Dinner rolls 6 pk', 'bakery', 'rolls'),
    ('Dinner rolls 12 pk', 'bakery', 'rolls'),
    ('6 white rolls', 'bakery', 'rolls'),
    ('12 white rolls', 'bakery', 'rolls'),
    ('White rolls 6 pk', 'bakery', 'rolls'),
    ('White rolls 12 pk', 'bakery', 'rolls')
]

chainList = ['Woolworths', 'Coles', 'Aldi']

for product in productList:
    if product[1] not in catList:
        print('You messed up:', product)
        exit()
    # Define the data to be inserted
    data = {
        "productID": {"S": str(randint(10000000,99999999))},
        "name": {"S": product[0]},
        "lower_name": {"S": product[0].strip().lower()},
        "category": {"S": product[1]},
        "subcategory": {"S": product[2]},
        "avgRating": {"N": str(round(random()*4+1,1))},
        "chain": {"S": chainList[randint(0,2)]},
        "quantity": {"N": str(randint(1,60))},
        "price": {"N": str(round((random()*9+1)**2,2))},
        "imageLocation": {"S": 'https://superprice-product-images.s3.ap-southeast-2.amazonaws.com/' + product[2] + '.jpg'},
        "isPromoted": {"BOOL": True if randint(0,1) == 1 else False}
    }

    # Insert the data into the DynamoDB table
    table_name = 'Product'
    response = dynamodb.put_item(TableName=table_name, Item=data)

    # Check the response
    print("Item added successfully:", response)