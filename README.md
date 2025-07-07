
**To Check Coverage:**
1- Clone the GitHub repo:
git clone https://github.com/anamikamishra-nagarro/store-discount.git
git checkout master

2- Run Tests with coverage using "mvn clean verify"

3- Open the coverage report in browser: target/site/jacoco/index.html


**To Test the functionality**
After running the application import below below curl in postman and execute

curl --location 'http://localhost:8080/api/bill/calculate' \
--header 'Content-Type: application/json' \
--data '{
"user": {
"name": "Abhishek",
"userType": "EMPLOYEE",
"registrationDate": "2023-05-15"
},
"products": [
{
"name": "Apple",
"price": 5,
"category": "GROCERIES"
},
{
"name": "Shampoo",
"price": 250.0,
"category": "OTHERS"
}
]
}
'
