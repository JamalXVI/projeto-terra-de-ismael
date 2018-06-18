import { User } from './user/user.model';
import { MatPaginatorIntl } from '@angular/material';

export const DEFAULT_LOGIN_NAME = 'login';
export const PAGE_SIZE_OPTIONS = [5, 10, 15, 20];
export const DEFAULT_PAGE_SIZE_OPTION = 10;
export function UserFilter(user: User, filters: any): boolean {
    const role = user.role === 0 ? 'Usuário' : 'Adminstrador';
    if ((!filters.name || user.name.includes(filters.name))
     && (!filters.role || user.role.toString().includes(filters.role) || role.includes(filters.role) )
        && (!filters.id || user.id.toString().includes(filters.id)) && (!filters.user || user.user.includes(filters.user))) {
        return true;
    }
    return false;
}
export class CustomPaginatorLabels extends MatPaginatorIntl {
  itemsPerPageLabel = 'Itens por Página';
  nextPageLabel     = 'Próxima Página';
  previousPageLabel = 'Página Anterior';
}
