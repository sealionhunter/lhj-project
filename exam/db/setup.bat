@echo off
@setlocal
pushd %~pd0

set /p confirm=All the data will be lost. Are you sure?
if /I '%confirm%' NEQ 'yes' exit /b 1;
set confirm=no
set /p confirm=Confire again?
if /I '%confirm%' NEQ 'yes' exit /b 1;

set /p password=please enter password:

mysql -u root --password=%password% -e "source db.sql"
mysql -u root --password=%password% -e "source dbinit.sql"

popd
@endlocal
@echo on