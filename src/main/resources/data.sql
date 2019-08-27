INSERT INTO category(id, name, parent_id) VALUES  (1, 'Soup', null);
INSERT INTO category(id, name, parent_id) VALUES  (2, 'Fish Soup', 1);
INSERT INTO category(id, name, parent_id) VALUES (3, 'Pasta Soup', 1);
INSERT INTO category(id, name, parent_id) VALUES (4, 'Pasta', null);
INSERT INTO category(id, name, parent_id) VALUES (5, 'Italy Pasta', 4);



INSERT INTO food(id, name, description, price, category_id) VALUES (1, 'Fishoup', 'Great and tasty!', 15.99, 2);
INSERT INTO food(id, name, description, price, category_id) VALUES (2, 'Buenotone', 'Awesome!', 11.60, 5);
INSERT INTO food(id, name, description, price, category_id) VALUES (3, 'Makarone', 'Woah greath thing!', 13.50, 5);
