# API design

## Auth

- POST /api/v1/auth/register
- POST /api/v1/auth/login

## Products

- POST /api/v1/products
- GET /api/v1/products?search=&page=&size=
- GET /api/v1/products/{id}
- PATCH /api/v1/products/{id}

## Warehouses

- POST /api/v1/warehouses
- GET /api/v1/warehouses
- GET /api/v1/warehouses/{id}

## Stocks

- GET /api/v1/stocks?warehouseId=...
- GET /api/v1/stocks/low?warehouseId=...

## Movements

- POST /api/v1/movements/in
- POST /api/v1/movements/out
- POST /api/v1/movements/adjust
- GET /api/v1/movements?warehouseId=&productId=&from=&to=&page=&size=
