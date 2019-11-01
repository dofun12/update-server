import { Injectable } from '@angular/core';
import {Toast} from "../_dto/toast";

@Injectable({
  providedIn: 'root'
})
export class ToastService {
  toasts: Toast[] = [];

  showInfo(header: string, body: string) {
    this.show(header, body, 'info');
  }

  showWarning(header: string, body: string) {
    this.show(header, body, 'warning');
  }

  showError(header: string, body: string) {
    this.show(header, body, 'error');
  }

  async show(header: string, body: string, type: string) {
    const toast: Toast  = new Toast();
    toast.body = body;
    toast.header = header;
    toast.type = type;
    this.toasts.push(toast);
    await this.delay(toast.delay || 2000);
    this.remove(toast);
  }

  remove(toast) {
    this.toasts = this.toasts.filter(t => t !== toast);
  }

  private delay(ms: number): Promise<boolean> {
    return new Promise(resolve => {
      setTimeout(() => {
        resolve(true);
      }, ms);
    });
  }
}
