@echo off
@setlocal
pushd %~pd0

set /p confirm=Please backup the db before you do this, Are you sure?
if /I '%confirm%' NEQ 'yes' exit /b 1;
set /p password=please enter password:

mysql -u root --password=%password% -e "source dbupdate02.sql" exam

popd
@endlocal
@echo on