start transaction;

use `Acme-Conference`;

revoke all privileges on `Acme-Conference`.*from 'acme-user'@'%';

revoke all privileges on `Acme-Conference`.*from 'acme-manager'@'%';

drop user 'acme-user'@'%';
drop user 'acme-manager'@'%';

drop database `Acme-Conference`;

commit;