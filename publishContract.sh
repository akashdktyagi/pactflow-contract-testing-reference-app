# To Convert a file to base64 on mac use
# base64 contractfile.json
curl -v --request POST  \
     --url 'http://localhost:8000/contracts/publish' \
     --header 'Authorization: Basic cGFjdDpwYWN0' \
     --header 'Content-Type: application/json' \
     --header 'Accept: application/json' \
     --data-raw '
     {
       "pacticipantName": "EmployerServiceAPI",
       "pacticipantVersionNumber": "v3",
       "branch": "master",
       "tags": ["main"],
       "buildUrl": "https://ci/builds/1234",
       "contracts": [
         {
           "consumerName": "EmployerServiceAPI",
           "providerName": "EmployeeServiceAPI",
           "specification": "pact",
           "contentType": "application/json",
           "content": "<base64 converted pact file. use base64 contractfile.json if on mac>"
         }
       ]
     }
     '
