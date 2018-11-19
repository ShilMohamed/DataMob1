import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmationInscriptionProfComponent } from './confirmation-inscription-prof.component';

describe('ConfirmationInscriptionProfComponent', () => {
  let component: ConfirmationInscriptionProfComponent;
  let fixture: ComponentFixture<ConfirmationInscriptionProfComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfirmationInscriptionProfComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmationInscriptionProfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
