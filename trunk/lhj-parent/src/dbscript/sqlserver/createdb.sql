USE [master]
GO

CREATE DATABASE [lhjdb]
GO

CREATE LOGIN [lhj] WITH PASSWORD=N'lhjdb', DEFAULT_DATABASE=[lhjdb], CHECK_EXPIRATION=OFF, CHECK_POLICY=OFF
GO

EXEC sys.sp_addsrvrolemember @loginame = N'lhj', @rolename = N'sysadmin'
GO

ALTER LOGIN [lhj] ENABLE
GO

