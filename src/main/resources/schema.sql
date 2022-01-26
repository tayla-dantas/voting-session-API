IF (EXISTS (SELECT *
                 FROM INFORMATION_SCHEMA.TABLES
                 WHERE TABLE_NAME = 'TBL_ASSOCIATES'))
BEGIN
    CREATE TABLE TBL_ASSOCIATES (
                                   id INT NOT NULL,
                                   vote VARCHAR(250) NOT NULL,
                                   PRIMARY KEY (id)
    );
END

IF (EXISTS (SELECT *
                 FROM INFORMATION_SCHEMA.TABLES
                 WHERE TABLE_NAME = 'TBL_SCHEDULES'))
BEGIN
    CREATE TABLE TBL_SCHEDULES (
                                   id INT NOT NULL,
                                   name VARCHAR(250) NOT NULL,
                                   PRIMARY KEY (id)
    );
END

IF (EXISTS (SELECT *
                 FROM INFORMATION_SCHEMA.TABLES
                 WHERE TABLE_NAME = 'TBL_VOTING_SESSIONS'))
BEGIN
   CREATE TABLE TBL_VOTING_SESSIONS (
                                  id INT NOT NULL,
                                  result INT NOT NULL,
                                  expireTime VARCHAR(250) NOT NULL,
                                  scheduleId INT NOT NULL
                                  PRIMARY KEY (id)
   );
END

