@echo off
@setlocal
pushd %~pd0

set /p password=please enter password:
set /p backupfile=please enter backup file:

mysql -u root --password=%password% -e "source %backupfile%" exam

popd
@endlocal
@echo on