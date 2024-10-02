-- Inserindo usuários
INSERT INTO users (display_name, city, state, zipcode, interested_in_peanut_allergies, interested_in_egg_allergies, interested_in_dairy_allergies) VALUES
('Alice', 'New York', 'NY', '10001', true, true, false);
INSERT INTO users (display_name, city, state, zipcode, interested_in_peanut_allergies, interested_in_egg_allergies, interested_in_dairy_allergies) VALUES
('Bob', 'Los Angeles', 'CA', '90001', false, true, true);
INSERT INTO users (display_name, city, state, zipcode, interested_in_peanut_allergies, interested_in_egg_allergies, interested_in_dairy_allergies) VALUES
('Charlie', 'Chicago', 'IL', '60601', true, false, true);

-- Inserindo restaurantes
INSERT INTO restaurant (name, address, cuisine_type, peanut_score, egg_score, dairy_score, overall_score) VALUES
('Veggie Haven', '123 Veggie St, New York, NY', 'Vegetarian', 4.5, 4.0, 4.5, 4.33);
INSERT INTO restaurant (name, address, cuisine_type, peanut_score, egg_score, dairy_score, overall_score) VALUES
('Meat Lovers', '456 Meat Ave, Los Angeles, CA', 'Steakhouse', 2.0, 3.5, 2.5, 2.67);
INSERT INTO restaurant (name, address, cuisine_type, peanut_score, egg_score, dairy_score, overall_score) VALUES
('Healthy Eats', '789 Health Blvd, Chicago, IL', 'Organic', null, null, 5.0, 5.0);

-- Inserindo avaliações de restaurantes
INSERT INTO dining_review (submitted_by, restaurant_id, peanut_score, egg_score, dairy_score, commentary, status) VALUES
('Alice', 1, 4.0, 4.5, null, 'Great vegetarian options!', 'ACCEPTED');
INSERT INTO dining_review (submitted_by, restaurant_id, peanut_score, egg_score, dairy_score, commentary, status) VALUES
('Bob', 2, 3.0, 3.0, 2.5, 'Good meat quality but could improve on dairy options.', 'ACCEPTED');
INSERT INTO dining_review (submitted_by, restaurant_id, peanut_score, egg_score, dairy_score, commentary, status) VALUES
('Charlie', 3, null, null, 5.0, 'Amazing organic meals!', 'PENDING');
