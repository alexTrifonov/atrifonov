--CREATE TABLE users_roles (id SERIAL PRIMARY KEY, user_id INTEGER REFERENCES users(id), role_id INTEGER REFERENCES roles(id));

--CREATE TABLE users (id serial PRIMARY KEY , login VARCHAR(100), password VARCHAR(100));

--INSERT INTO users_roles (user_id, role_id) VALUES (1, 2);

--INSERT INTO users(login, password) values ('Santer', '$2a$10$YxXO7czEBdH1dBMWTy4pq.Y6vVHaGk0ZiupFxhSDyIEHSIioztfS2');


--INSERT INTO users (login, password) VALUES ('Santer', '$2a$10$RikA1171REMRwnrFvpkSteWfwAo/bUVj7AC1cZ7WG7EM6gTFgmm56');
--INSERT INTO roles (name) VALUES ('user'), ('admin');
--INSERT INTO users_roles (user_id, role_id) VALUES (1,1);
--INSERT INTO engines (engine_type) VALUES ('petrol'), ('diesel'), ('hybrid'), ('electric'), ('gas');
--INSERT INTO make_cars(make) VALUES ('BMV'), ('Audi'), ('Honda'), ('Toyota');
--INSERT INTO auto_models(model, make_id) VALUES ('1M',1), ('X3', 1), ('X4', 1), ('X5', 1), ('X6', 1), ('A1', 2), ('A2', 2), ('A3', 2), ('A4', 2),
					--('Civic', 3), ('Accord', 3), ('CR-V', 3), ('CR-X', 3), ('Allex', 4), ('Auris', 4), ('Avensis', 4), ('Camry', 4);
--INSERT INTO bodies(body_type) VALUES('hatchback'), ('sedan'), ('coupe'), ('jeep');
--INSERT INTO drives(drive_type) VALUES('forward'), ('rear'), ('fwd');
--INSERT INTO transmissions(transm_type) VALUES('mechanical'), ('automatic'), ('robot'), ('variator');