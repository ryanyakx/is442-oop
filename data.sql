INSERT INTO membership (membership_ID, name, place_of_interest, max_pass_per_loan, status)
VALUES (1,'Mandai Wildlife Reserve','Singapore Zoo', 2, 1);

INSERT INTO membership (membership_ID, name, place_of_interest, max_pass_per_loan, status)
VALUES (2,'Mandai Wildlife Reserve','River Wonder', 2, 1);

INSERT INTO corporate_pass (card_Number, membership_ID, card_Type, replacement_Fee, status)
VALUES ('1234',1,'Physical', 50, 1),('1235',1,'Physical', 50, 1),('1236',2,'Physical', 50, 1);

INSERT INTO constant (id , max_loan_per_month)
VALUES (1 , 3);

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

INSERT INTO users (id, contact_num, email, password, username)
VALUES (1, '90009000', 'masteradmin@sportsschool.edu.sg', '$2a$10$fs1JcANccaIlarRdj61JrOYEwnQ.BfUkA6jevy0NUa6tlmres41HW', 'master admin');

INSERT INTO user_roles(user_id, role_id)
VALUES (1, 2);

INSERT INTO user (employee_ID, name, email, contact_Num, password, status, is_admin)
VALUES (1, 'master admin', 'masteradmin@sportsschool.edu.sg', '90009000', 'no password', '1', '2');
