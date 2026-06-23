-- Expanded Seed Data with Authentic Localized Records
INSERT INTO users (name, email, password, role, created_at, updated_at) VALUES 
('Aarav Sharma', 'aarav.sharma@example.com', 'hashed_secure_pass_1', 'ROLE_USER', NOW(), NOW()),
('Ananya Iyer', 'ananya.iyer@example.com', 'hashed_secure_pass_2', 'ROLE_USER', NOW(), NOW()),
('Rohan Verma', 'rohan.verma@example.com', 'hashed_admin_pass', 'ROLE_ADMIN', NOW(), NOW()),
('Diya Nair', 'diya.nair@example.com', 'hashed_secure_pass_3', 'ROLE_USER', NOW(), NOW()),
('Kabir Malhotra', 'kabir.malhotra@example.com', 'hashed_secure_pass_4', 'ROLE_USER', NOW(), NOW()),
('Ishaan Banerjee', 'ishaan.banerjee@example.com', 'hashed_secure_pass_5', 'ROLE_USER', NOW(), NOW()),
('Meera Joshi', 'meera.joshi@example.com', 'hashed_secure_pass_6', 'ROLE_USER', NOW(), NOW()),
('Vivaan Reddy', 'vivaan.reddy@example.com', 'hashed_secure_pass_7', 'ROLE_USER', NOW(), NOW());

INSERT INTO categories (name, description, parent_category_id) VALUES 
('Electronics & Gadgets', 'Mobile phones, audio gear, and technical hardware components', NULL),
('Home Appliances', 'Smart devices and everyday household systems', NULL),
('Accessories', 'Wearables, charging gear, and laptop stands', NULL);

INSERT INTO products (name, description, price, stock, category_id, is_active, created_at, updated_at) VALUES 
('Boat Rockerz Headphones', 'Wireless over-ear headphones with active noise cancellation', 2499.00, 120, 1, TRUE, NOW(), NOW()),
('Portronics USB-C Cable', 'Heavy duty braided fast charging cable for smartphones', 399.00, 250, 3, TRUE, NOW(), NOW()),
('Mi Smart Air Purifier', 'High efficiency particulate air filter with real-time tracking', 9999.00, 45, 2, TRUE, NOW(), NOW()),
('OnePlus Nord Buds', 'True wireless in-ear earbuds with deep bass and IP55 rating', 2199.00, 80, 1, TRUE, NOW(), NOW()),
('Spigen Tough Armor Case', 'Dual layer protective cover engineered for ultimate phone safety', 1499.00, 150, 3, TRUE, NOW(), NOW()),
('Realme 10000mAh Power Bank', '18W fast two-way charging backup power brick', 1299.00, 95, 3, TRUE, NOW(), NOW()),
('Philips Air Fryer', 'Digital touch screen healthy air frying kitchen tool', 8499.00, 30, 2, TRUE, NOW(), NOW()),
('Logitech Wireless Mouse', 'Ergonomic multi-device office mouse with silent clicks', 1199.00, 110, 1, TRUE, NOW(), NOW());