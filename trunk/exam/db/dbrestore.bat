@echo off
@setlocal
pushd %~pd0

set /p confirm=All the data will be lost. Are you sure?
if /I '%confirm%' NEQ 'yes' exit /b 1;
set /p confirm=Confire again?
if /I '%confirm%' NEQ 'yes' exit /b 1;


set /p password=please enter password:
set /p backupfile=please enter backup file:

mysql -u root --password=%password% -e "source %backupfile%" exam

popd
@endlocal
@echo on