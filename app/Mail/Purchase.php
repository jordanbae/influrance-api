<?php



namespace App\Mail;

use App\Models\AgentCustomerOrder;
use Barryvdh\DomPDF\Facade\Pdf;
use Illuminate\Bus\Queueable;
use Illuminate\Mail\Mailable;
use Illuminate\Queue\SerializesModels;


class Purchase extends Mailable
{
    use Queueable, SerializesModels;
    public $request;
    /**
     * Create a new message instance.
     *
     * @return void
     */
    public function __construct($request)
    {
        $this->request = $request;
    }

    /**
     * Build the message.
     *
     * @return $this
     */
    public function build()
    {
        $order = AgentCustomerOrder::find($this->request['order_id'])->toArray();
        $pdf = Pdf::loadView('pdf.index', ['order' => $order, 'request' => $this->request]);
        return $this->subject('Thank you for your order! Influrance Co., Ltd.')
            ->markdown('emails.purchase')
            ->with('prefix', $this->request['prefix'])
            ->with('firstname', $this->request['firstname'])
            ->with('lastname', $this->request['lastname'])
            ->attachData($pdf->output(), $this->request['prefix']. $this->request['firstname'] . $this->request['lastname'] . ' Order Details' . '.pdf');
    }
}
