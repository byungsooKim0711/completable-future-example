# completable-future-example
#### CompletableFuture 연습

:: Request Example1)
```
- **Request**: curl -XGET "http://localhost:8080/api/coffee/latte,AMERICANO,MOCHA"
- **Response**: [{"name":"LATTE","price":1700},{"name":"AMERICANO","price":1400},{"name":"MOCHA","price":2000}]
```

:: Request Example2)
```
- **Request**: curl -XGET "http://localhost:8080/api/coffee/MOCHA"
- **Response**: [{"name":"MOCHA","price":2000}]
```