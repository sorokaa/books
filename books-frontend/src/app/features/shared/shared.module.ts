import {NgModule} from "@angular/core";
import {HeaderComponent} from "./header/header.component";
import {RouterLink} from "@angular/router";

@NgModule({
    declarations: [
        HeaderComponent
    ],
    imports: [
        RouterLink
    ],
    exports: [
        HeaderComponent
    ]
})
export class SharedModule {

}
