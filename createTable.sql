IF OBJECT_ID ('dbo.test_jay') IS NOT NULL
	DROP TABLE dbo.test_jay
GO

CREATE TABLE dbo.test_jay
	(
	description   VARCHAR (255) NOT NULL,
	request            TEXT NOT NULL,
	response            VARCHAR (1000) NULL,
	expectReturncode VARCHAR (50) NULL,
	actualReturncode VARCHAR (50) NULL,
	successFlag   TINYINT NULL
	)
GO