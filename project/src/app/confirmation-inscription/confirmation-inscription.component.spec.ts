import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmationInscriptionComponent } from './confirmation-inscription.component';

describe('ConfirmationInscriptionComponent', () => {
  let component: ConfirmationInscriptionComponent;
  let fixture: ComponentFixture<ConfirmationInscriptionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfirmationInscriptionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmationInscriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
