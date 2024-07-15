import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PartComponent } from './part/part.component';
import { PartDetailComponent } from './part-detail/part-detail.component';
import { LoginComponent } from './login/login.component';
import { InventoryComponent } from './inventory/inventory.component';
import { PartSearchComponent } from './part-search/part-search.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { PreBuiltsComponent } from './pre-builts/pre-builts.component';

const routes: Routes = [
  { path: 'parts', component: PartComponent },
  { path: 'detail/:id', component: PartDetailComponent },
  { path: 'login',  component: LoginComponent},
  { path: 'cart', component: ShoppingCartComponent },
  { path: 'inventory', component: InventoryComponent},
  { path: 'search', component: PartSearchComponent},
  { path: 'prebuilts', component: PreBuiltsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }