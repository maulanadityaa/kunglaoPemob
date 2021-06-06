<?php

namespace App\Http\Controllers;

use Illuminate\Database\Eloquent\ModelNotFoundException;
use Illuminate\Http\Request;
use Psr\Log\LoggerInterface;
// require _DIR_ . '/vendor/autoload.php';
use Illuminate\Support\Facades\DB;
use App\Models\Peminjaman;
use App\Models\Book;

class PeminjamanController extends Controller
{
    public function showPinjaman($id_user)
    {

        if ($id_user) {
            $Books = DB::table('books')
                ->join('peminjamans', 'books.id', '=', 'peminjamans.id_buku')
                ->where('peminjamans.id_user', '=', $id_user)
                ->select('books.*', 'peminjamans.id_buku')
                ->get();

            return $Books;
        } else {
            return response()->json([
                'message' => 'Anda Belum meminjam'
            ], 404);
        }
    }

    public function store(Request $request)
    {
        $this->validate($request, [
            'id_user' => 'required',
            'id_buku' => 'required'
        ]);

        $pinjaman = Peminjaman::create(
            $request->only(['id_user', 'id_buku'])
        );

        $buku = Book::where('id', $request->id_buku)->first();
        $stok = $buku->stock - 1;
        Book::where('id', $request->id_buku)
            ->update(['stock' => $stok]);

        return response()->json([
            $pinjaman
        ], 201);
    }


    public function destroy($id_user, $id_buku)
    {
        $peminjaman = Peminjaman::where([
            ['id_user', '=', $id_user],
            ['id_buku', '=', $id_buku]
        ])->first();
        if ($peminjaman) {
            $peminjaman->delete();
            return response()->json([
                'deleted' => true
            ], 200);
        } else {
            return response()->json([
                'error' => [
                    'message' => 'anda belum meminjam'
                ]
            ], 404);
        }
    }
}
