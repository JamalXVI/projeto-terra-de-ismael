import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource, MatPaginatorIntl } from '@angular/material';
import { UserService } from '../core/user/user.service';
import { DEFAULT_PAGE_SIZE_OPTION, PAGE_SIZE_OPTIONS, UserFilter, CustomPaginatorLabels } from '../core/constants';
import { Subject } from 'rxjs/internal/Subject';
import { map } from 'rxjs/internal/operators/map';
import { distinctUntilChanged } from 'rxjs/operators';
import { set } from 'lodash';
import { User } from '../core/user/user.model';

@Component({
  selector: 'app-tabela',
  templateUrl: './tabela.component.html',
  styleUrls: ['./tabela.component.css'],
  providers: [{ provide: MatPaginatorIntl, useClass: CustomPaginatorLabels}]
})
export class TabelaComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  search$: Subject<any> = new Subject<any>();
  filters: any = {};
  dataSource: MatTableDataSource<User>;
  defaultPageSize: Number = DEFAULT_PAGE_SIZE_OPTION;
  pageSizes: Number[] = PAGE_SIZE_OPTIONS;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'user', 'name', 'role'];
  constructor(private userService: UserService) {
    this.search$.pipe(map(query => query || ''), distinctUntilChanged(),
      map((column: { search: any, prop: string }) => set(this.filters, column.prop, column.search)))
      .subscribe(filters => this.dataSource.filter = JSON.stringify(filters));
  }

  ngOnInit() {
    this.dataSource = new MatTableDataSource(this.userService.gerUsers());
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.dataSource.filterPredicate = this.createFilter();
  }

  createFilter(): (data: User, filter: string) => boolean {
    const filterFunction = function (data, filter): boolean {
      const searchTerms = JSON.parse(filter);
      return UserFilter(data, searchTerms);
    };
    return filterFunction;
  }
}
