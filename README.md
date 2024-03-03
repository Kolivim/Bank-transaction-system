# Bank-transaction-system


docker run -it -d --name btspostgres -e POSTGRES_PASSWORD=transactions -e POSTGRES_USER=transactions -e POSTGRES_DB=transactions --network bts -p 5432:5432 postgres:15.4