USE [master]
GO

IF  EXISTS (SELECT name FROM sys.databases WHERE name = N'lhj-db')
DROP DATABASE [lhj-db]
GO

IF  EXISTS (SELECT * FROM sys.server_principals WHERE name = N'lhj')
DROP LOGIN [lhj]
GO
