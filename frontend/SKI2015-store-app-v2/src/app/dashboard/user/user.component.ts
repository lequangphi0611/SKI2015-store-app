import { Observable, BehaviorSubject } from "rxjs";
import { AdminService } from "./../../service/admin/admin.service";
import { UploadImageService } from "./../../service/upload-image/upload-image.service";
import { ImageChooseComponent } from "./../image-choose/image-choose.component";
import { Admin } from "./../../model/Admin";
import { FormBuilder, FormGroup, NgForm } from "@angular/forms";
import { Component, OnInit, ViewChild } from "@angular/core";

@Component({
  selector: "app-user",
  templateUrl: "./user.component.html",
  styleUrls: ["./user.component.css"],
})
export class UserComponent implements OnInit {
  @ViewChild(ImageChooseComponent) imageChooseComponent: ImageChooseComponent;

  userForm: FormGroup;

  admin: BehaviorSubject<Admin> = new BehaviorSubject<Admin>(null);

  imageSelected: File;

  admins$: Observable<Admin[]>;

  editMode = false;

  @ViewChild("myForm") myForm: NgForm;

  constructor(
    private fb: FormBuilder,
    private uploadImageService: UploadImageService,
    private adminService: AdminService
  ) {
    this.builForm();
  }

  ngOnInit() {
    this.fetchAdmins();
  }

  fetchAdmins() {
    this.admins$ = this.adminService.getAdmins();
  }

  builForm(): void {
    this.userForm = this.fb.group({
      firstname: [""],
      lastname: [""],
      birthday: [""],
      username: [""],
      password: [""],
    });
  }

  onSelectedImage(file: File) {
    this.imageSelected = file;
  }

  async onSubmit() {
    const admin: Admin = this.userForm.value;

    if (!this.editMode) {
      admin.avartarPath = await this.uploadImage(admin.firstname);
      await this.adminService.insertAdmin(admin).toPromise();
    } else {
      const oldAdmin = this.admin.value;
      admin.avartarPath = this.imageSelected
        ? await this.uploadImage(admin.firstname)
        : oldAdmin.avartarPath;
      admin.id = oldAdmin.id;
      await this.adminService.updateAdmin(admin).toPromise();
    }

    this.fetchAdmins();
    this.onReset();
  }

  uploadImage(adminName: string): Promise<string> {
    return new Promise<string>((resolve, reject) => {
      setTimeout(() => {
        const fd = new FormData();
        fd.append("file", this.imageSelected);
        this.uploadImageService.upload(fd, adminName).subscribe(
          response => {
            resolve(response.relativePath);
          },
          err => reject(err)
        );
      }, 100);
    });
  }

  enableEditMode(admin: Admin) {
    this.admin.next(admin);
    this.imageChooseComponent.setImageUrl(admin.avartarPath);
    this.imageChooseComponent.imageName.next(
      admin.avartarPath.substring(
        admin.avartarPath.lastIndexOf("/") + 1,
        admin.avartarPath.length
      )
    );
    this.editMode = true;
  }

  onReset() {
    this.admin.next(null);
    this.imageChooseComponent.reset();
    this.myForm.resetForm();
    this.editMode = false;
  }

  delete(id: number): void {
    this.adminService.deleteAdminBy(id)
      .subscribe({
        complete: () => {
          this.fetchAdmins();
        }
      });
  }
}
