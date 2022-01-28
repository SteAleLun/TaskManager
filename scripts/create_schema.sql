CREATE SCHEMA TM;

CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(
    'derby.user.tm', 'tm');

CREATE TABLE List (
    LIST_ID INTEGER NOT NULL
                  PRIMARY KEY GENERATED ALWAYS AS IDENTITY
                      (START WITH 1, INCREMENT BY 1),
    NAME VARCHAR (15),
    CREATE_DATE TIMESTAMP
);

insert into list values (DEFAULT , 'Inbox', NULL);
insert into list values (DEFAULT , 'Private', NULL);
insert into list values (DEFAULT , 'Work', NULL);

CREATE TABLE Task (
    TASK_ID INTEGER NOT NULL
                 PRIMARY KEY GENERATED ALWAYS AS IDENTITY
                    (START WITH 1, INCREMENT BY 1),
    DESCRIPTION VARCHAR (50),
    LIST_ID INTEGER,
    DUE_DATE TIMESTAMP,
    COMPLETED INTEGER DEFAULT 0,
    FOREIGN KEY (LIST_ID)
    REFERENCES List (LIST_ID)
);