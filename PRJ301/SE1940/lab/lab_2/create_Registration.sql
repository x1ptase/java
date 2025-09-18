USE SampleDB;
SELECT * FROM sys.tables;

CREATE TABLE Registration (
    UserName NVARCHAR(50) PRIMARY KEY,
    Password NVARCHAR(50) NOT NULL,
    LastName NVARCHAR(50),
    isAdmin BIT NOT NULL
);

INSERT INTO Registration (UserName, Password, LastName, isAdmin)
VALUES 
('U001', '123', 'Tom', 1),
('U002', '111', 'John', 0),
('U003', '111', 'Mark', 0),
('U004', '111', 'Marry', 0),
('U005', '123', 'Kate', 1);

SELECT * FROM Registration WHERE UserName='U002' AND Password='111';

DELETE FROM Registration
WHERE UserName = 'sa' AND Password = '12345';

INSERT INTO Registration (UserName, Password, LastName, isAdmin)
VALUES ('sa', '12345', 'System Admin', 1);

INSERT INTO Registration (UserName, Password, LastName, isAdmin)
VALUES ('U006', '123', 'test', 1);

DELETE FROM Registration
WHERE UserName = 'U006' AND Password = '123';

SELECT * FROM Registration WHERE UserName='U009' AND Password='123';