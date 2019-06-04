import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImageChooseComponent } from './image-choose.component';

describe('ImageChooseComponent', () => {
  let component: ImageChooseComponent;
  let fixture: ComponentFixture<ImageChooseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ImageChooseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImageChooseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
