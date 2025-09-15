CREATE DATABASE SuperDuperMarkt;

CREATE TABLE
  SuperDuperMarkt.product (
    id int unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
    type varchar(255),
    name varchar(255),
    expired_at date,
    added_to_supply date,
    price decimal(10, 2),
    quality int
  );

INSERT INTO SuperDuperMarkt.product VALUES
(1, 'CHEESE', 'Gouda', '2025-12-15', '2025-09-15', 7.99, 49),
(2, 'TRIVIAL', 'Mehl', '2026-09-15', '2025-09-15', 3.5, 50),
(3, 'WINE', 'Wein', '2025-12-31', '2025-09-15', 13.99, 25),
(4, 'BREAD', 'Bauernbrot', '2025-09-21', '2025-09-15', 6.99, 35),