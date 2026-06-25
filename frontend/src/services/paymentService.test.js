import { PaymentService } from './paymentService';
import axios from 'axios';

jest.mock('axios');

describe('PaymentService', () => {
  it('posts ticket details to the payments endpoint', async () => {
    axios.post.mockResolvedValue({ status: 200 });
    const service = new PaymentService();

    await service.sendTicketDetail({ email: 'user@example.com', phone: '05000000000' });

    expect(axios.post).toHaveBeenCalledWith(
      'http://localhost:8080/api/movie/payments/sendTicketDetail',
      { email: 'user@example.com', phone: '05000000000' }
    );
  });
});
