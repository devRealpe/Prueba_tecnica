import { Component } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { InputGroupModule } from 'primeng/inputgroup';
import { InputGroupAddonModule } from 'primeng/inputgroupaddon';
import { HttpClientModule } from '@angular/common/http';
import { provideHttpClient } from '@angular/common/http';
import { BackendService, User } from '../../core/backend.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-inicio-component',
  imports: [ButtonModule, TableModule, InputGroupModule, InputGroupAddonModule, HttpClientModule, FormsModule],
  templateUrl: './inicio-component.html',
  styleUrls: ['./inicio-component.scss'],
  standalone: true
})
export class InicioComponent {
  // Mostrar usuarios en la tabla
  users: User[] = [];

   // Variables para el formulario
  text1: string = '';
  text2: string = '';

    constructor(private backendService: BackendService) {} //  Inyección del servicio

  ngOnInit(): void {
    this.cargarUsuarios(); //  Se ejecuta al iniciar el componente
  }

  cargarUsuarios(): void {
    this.backendService.listItems().subscribe({
      next: (data) => {
        this.users = data; // Llena la tabla con datos reales
      },
      error: (err) => {
        console.error('Error al cargar usuarios:', err);
      }
    });
  }

  crearUsuario(): void {
  const nuevoUsuario: User = {
    name: this.text1,
    email: this.text2
  };

  this.backendService.createItem(nuevoUsuario).subscribe({
    next: (data) => {
      console.log('Usuario creado:', data);

      // Recargar tabla
      this.cargarUsuarios();

      // Limpiar formulario
      this.text1 = '';
      this.text2 = '';
    },
    error: (err) => {
      console.error('Error al crear usuario:', err);
    }
  });
}
}
