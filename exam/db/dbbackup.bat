@echo off
@setlocal
pushd %~pd0

set /p password=please enter password:
@set now=%date:~0,4%%date:~5,2%%date:~8,2%%time:~0,2%%time:~3,2%%time:~6,2%%time:~9,2%

mysqldump -u root --password=%password% exam > exam_%now%.sql

popd
@endlocal
@echo on