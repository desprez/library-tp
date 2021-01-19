-- SQL script for insert data (rename with SQL extension to activate)

INSERT INTO LIBRARY (ID, ADDRESS_CITY, ADDRESS_NUMBER, ADDRESS_POSTALCODE, ADDRESS_STREET, DIRECTOR_NAME, DIRECTOR_SURNAME, TYPE) VALUES (1, 'PARIS', 11, 75013, 'Quai François Mauriac', 'Hervé', 'François', 'NATIONAL');
INSERT INTO LIBRARY (ID, ADDRESS_CITY, ADDRESS_NUMBER, ADDRESS_POSTALCODE, ADDRESS_STREET, DIRECTOR_NAME, DIRECTOR_SURNAME, TYPE) VALUES (2, 'MONTREUIL', 10, 93100, ' Rue Valette, ', 'Pascal', 'Remy', 'PUBLIC');
INSERT INTO LIBRARY (ID, ADDRESS_CITY, ADDRESS_NUMBER, ADDRESS_POSTALCODE, ADDRESS_STREET, DIRECTOR_NAME, DIRECTOR_SURNAME, TYPE) VALUES (3, 'PARIS', 154, 75015, 'Rue Lecourbe', 'Simone', 'Dupuis', 'PUBLIC');
INSERT INTO LIBRARY (ID, ADDRESS_CITY, ADDRESS_NUMBER, ADDRESS_POSTALCODE, ADDRESS_STREET, DIRECTOR_NAME, DIRECTOR_SURNAME, TYPE) VALUES (4, 'PARIS', 2, 75004, 'Place Baudoyer', 'Nicolas', 'Simon', 'PUBLIC');
INSERT INTO BOOK (ID, AUTHOR, ISBN, LITERARY_GENRE, NUMBER_OF_PAGE, TITLE, LIBRARY_ID) VALUES (1, 'Pierre De Ronsard', '2401045805', 'TRAGEDY', '125', 'Mignonne allons voir si la rose et autres poèmes', 1);
INSERT INTO BOOK (ID, AUTHOR, ISBN, LITERARY_GENRE, NUMBER_OF_PAGE, TITLE, LIBRARY_ID) VALUES (2, 'Miguel de Cervantes', '843762214X', 'COMEDY', '200', 'Don Quixote', 2);
INSERT INTO BOOK (ID, AUTHOR, ISBN, LITERARY_GENRE, NUMBER_OF_PAGE, TITLE, LIBRARY_ID) VALUES (3, 'Dan Brown', '052556585X', 'TRAGEDY', '310', 'Da Vinci Code', 2);
INSERT INTO BOOK (ID, AUTHOR, ISBN, LITERARY_GENRE, NUMBER_OF_PAGE, TITLE, LIBRARY_ID) VALUES (4, 'Hervé Le Tellier', '207289509X', 'TRAGEDY', '336', 'L''anomalie ', 3);
INSERT INTO BOOK (ID, AUTHOR, ISBN, LITERARY_GENRE, NUMBER_OF_PAGE, TITLE, LIBRARY_ID) VALUES (5, 'Newman, Sam', '1491950358', 'TECHNICAL', '250', 'Building Microservices', 4);
