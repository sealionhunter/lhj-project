@echo off
@setlocal
pushd %~pd0

set /p password=please enter password:

mysql -u root --password=%password% -e "source db.sql"
mysql -u root --password=%password% -e "source dbinit.sql"

popd
@endlocal
@echo on