USE [master]
GO

CREATE DATABASE [lhj-db]
GO

CREATE LOGIN [lhj] WITH PASSWORD=N'lhj-db', DEFAULT_DATABASE=[lhj-db]
GO

EXEC sys.sp_addsrvrolemember @loginame = N'lhj', @rolename = N'sysadmin'
GO

ALTER LOGIN [lhj] ENABLE
GO

