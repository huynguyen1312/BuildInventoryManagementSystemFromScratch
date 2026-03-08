# Domain Model

- [diagram](ERDia.drawio.svg)
  
## Product

Represents an item that can be stored in a warehouse.

| Field | Data Type | Key / Constraint |

|---|---|---|
| id | UUID | Primary Key |
| sku | VARCHAR(100) | Unique, Not Null |
| name | VARCHAR(255) | Not Null |
| reorder_point | INTEGER | Default 0 |

---

## Warehouse

Represents a physical storage location.

| Field | Data Type | Key / Constraint |

|---|---|---|
| id | UUID | Primary Key |
| name | VARCHAR(255) | Not Null |
| address | VARCHAR(500) | Nullable |

---

## Stock

Represents the current quantity of one product in one warehouse.

| Field | Data Type | Key / Constraint |

|---|---|---|
| id | UUID | Primary Key |
| product_id | UUID | Foreign Key, Not Null |
| warehouse_id | UUID | Foreign Key, Not Null |
| quantity | INTEGER | Not Null, Check >= 0 |

Additional constraint:

- Unique(product_id, warehouse_id)

---

## StockMovement

Represents a stock change event such as stock in or stock out.

| Field | Data Type | Key / Constraint |

|---|---|---|
| id | UUID | Primary Key |
| product_id | UUID | Foreign Key, Not Null |
| warehouse_id | UUID | Foreign Key, Not Null |
| type | VARCHAR(20) | Not Null |
| quantity | INTEGER | Not Null, Check > 0 |
| created_at | TIMESTAMP | Not Null |
  