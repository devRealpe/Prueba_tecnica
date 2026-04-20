import { Component, OnInit } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { InputTextModule } from 'primeng/inputtext';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { BackendService, User } from '../../core/backend.service';

@Component({
  selector: 'app-inicio-component',
  standalone: true,
  imports: [
    CommonModule,
    ButtonModule,
    TableModule,
    InputTextModule,
    FormsModule
  ],
  templateUrl: './inicio-component.html',
  styleUrls: ['./inicio-component.scss']
})
export class InicioComponent implements OnInit {
  users: User[] = [];

  // Campos del formulario
  formName: string = '';
  formEmail: string = '';

  // Para saber si estamos editando (null = modo creación)
  editingId: number | null = null;

  // Mensajes de feedback
  errorMsg: string = '';
  successMsg: string = '';

  constructor(private backendService: BackendService) {}

  ngOnInit(): void {
    this.cargarUsuarios();
  }

  cargarUsuarios(): void {
    this.backendService.listItems().subscribe({
      next: (data) => {
        this.users = data;
      },
      error: (err) => {
        this.errorMsg = 'Error al cargar usuarios.';
        console.error(err);
      }
    });
  }

  // Prepara el formulario para editar un usuario existente
  editarUsuario(user: User): void {
    this.editingId = user.id ?? null;
    this.formName = user.name;
    this.formEmail = user.email;
    this.errorMsg = '';
    this.successMsg = '';
  }

  // Guarda: crea o actualiza según si editingId tiene valor
  guardarUsuario(): void {
    this.errorMsg = '';
    this.successMsg = '';

    if (!this.formName.trim() || !this.formEmail.trim()) {
      this.errorMsg = 'Nombre y correo son obligatorios.';
      return;
    }

    const payload: User = { name: this.formName, email: this.formEmail };

    if (this.editingId !== null) {
      // Actualizar
      this.backendService.updateItem(this.editingId, payload).subscribe({
        next: () => {
          this.successMsg = 'Usuario actualizado correctamente.';
          this.resetFormulario();
          this.cargarUsuarios();
        },
        error: (err) => {
          this.errorMsg = err.error?.error ?? 'Error al actualizar usuario.';
        }
      });
    } else {
      // Crear
      this.backendService.createItem(payload).subscribe({
        next: () => {
          this.successMsg = 'Usuario creado correctamente.';
          this.resetFormulario();
          this.cargarUsuarios();
        },
        error: (err) => {
          this.errorMsg = err.error?.error ?? 'Error al crear usuario.';
        }
      });
    }
  }

  eliminarUsuario(id: number): void {
    if (!confirm('¿Estás seguro de que deseas eliminar este usuario?')) return;

    this.backendService.deleteItem(id).subscribe({
      next: () => {
        this.successMsg = 'Usuario eliminado correctamente.';
        this.cargarUsuarios();
      },
      error: (err) => {
        this.errorMsg = err.error?.error ?? 'Error al eliminar usuario.';
      }
    });
  }

  cancelarEdicion(): void {
    this.resetFormulario();
  }

  private resetFormulario(): void {
    this.formName = '';
    this.formEmail = '';
    this.editingId = null;
  }
}