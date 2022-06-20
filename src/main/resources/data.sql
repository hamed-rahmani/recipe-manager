
--------------user_tbl----------
INSERT INTO user_tbl(user_name) values('Admin');
INSERT INTO user_tbl(user_name) values('John');
INSERT INTO user_tbl(user_name) values('Stephan');

-----------ingredient----------
INSERT INTO ingredient(name,vegetable) values('Tomato', true);
INSERT INTO ingredient(name,vegetable) values('Cucumber', true);
INSERT INTO ingredient(name,vegetable) values('Potato', true);
INSERT INTO ingredient(name,vegetable) values('Broccoli', true);
INSERT INTO ingredient(name,vegetable) values('Zucchini', true);
INSERT INTO ingredient(name,vegetable) values('Spinach', true);

INSERT INTO ingredient(name,vegetable) values('Rice', false);
INSERT INTO ingredient(name,vegetable) values('Beef', false);
INSERT INTO ingredient(name,vegetable) values('Salmon', false);
INSERT INTO ingredient(name,vegetable) values('Prawn', false);
INSERT INTO ingredient(name,vegetable) values('Tuna', false);
INSERT INTO ingredient(name,vegetable) values('Ham', false);

------------instruction-----------

INSERT INTO instruction(name) values('Stove');
INSERT INTO instruction(name) values('Barbecue');
INSERT INTO instruction(name) values('Microwave');
INSERT INTO instruction(name) values('Airfryer');

-----------------------------------

INSERT INTO recipe(name,vegetable,comment,serve_number,user_id,instruction_id) values ('test',false,'not vegetable',2,1,1);
INSERT INTO recipe(name,vegetable,comment,serve_number,user_id,instruction_id) values ('test2',true,' vegetable',5,1,2);
INSERT INTO recipe(name,vegetable,comment,serve_number,user_id,instruction_id) values ('test3',true,' vegetable',5,null,2);

INSERT INTO recipe_detail(ingredient_amount,recipe_id,ingredient_id) values (100,1,1);
INSERT INTO recipe_detail(ingredient_amount,recipe_id,ingredient_id) values (200,1,7);
INSERT INTO recipe_detail(ingredient_amount,recipe_id,ingredient_id) values (250,2,1);
INSERT INTO recipe_detail(ingredient_amount,recipe_id,ingredient_id) values (250,2,1);
INSERT INTO recipe_detail(ingredient_amount,recipe_id,ingredient_id) values (250,3,1);
INSERT INTO recipe_detail(ingredient_amount,recipe_id,ingredient_id) values (500,3,3);

-------------------------------------
