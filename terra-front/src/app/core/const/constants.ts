
import { MatPaginatorIntl } from '@angular/material';
import { User } from '../user/user.model';

export const DEFAULT_LOGIN_NAME = 'tokenDeAcesso';
export const DEFAULT_USER_STORE = 'usuario';
export const PAGE_SIZE_OPTIONS = [5, 10, 15, 20];
export const DEFAULT_PAGE_SIZE_OPTION = 10;
export const DEFAULT_SEARCH_TERMS_VALUE = 3;

export function UserFilter(user: User, filters: any): boolean {
    const role = user.role === 0 ? 'Usuário' : 'Adminstrador';
    if ((!filters.nome || user.nome.includes(filters.name))
     && (!filters.role || user.role.toString().includes(filters.role) || role.includes(filters.role) )
        && (!filters.id || user.id.toString().includes(filters.id)) && (!filters.user || user.usuario.includes(filters.user))) {
        return true;
    }
    return false;
}

export class CustomPaginatorLabels extends MatPaginatorIntl {
  itemsPerPageLabel = 'Itens por Página';
  nextPageLabel     = 'Próxima Página';
  previousPageLabel = 'Página Anterior';
}
export const FORMATOS = {
  parse: {
    dateInput: 'DD/MM/YYYY',
  },
  display: {
    dateInput: 'DD/MM/YYYY',
    monthYearLabel: 'MM YYYY',
    dateA11yLabel: 'DD/MM/YYYY',
    monthYearA11yLabel: 'MM YYYY',
  },
};