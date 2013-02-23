@echo off
@setlocal
pushd %~pd0

set /p password=please enter password:
@set today=%date:~0,4%%date:~5,2%%date:~8,2%

mysqldump -u root --password=%password% exam > exam_%today%.sql

popd
@endlocal
@echo on