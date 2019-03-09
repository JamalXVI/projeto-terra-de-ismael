import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

import { Observable } from 'rxjs/internal/Observable';
import { of } from 'rxjs';

import { User } from './user.model';
import { AbstractUserService } from './abstract-user-service.model';
import { ErrorsService } from '../errors/errors.service';

@Injectable({ providedIn: 'root', })
export class UserMockService extends AbstractUserService {
    private users: User[];
    constructor(
        protected router: Router,
        protected http: HttpClient,
        protected errorService: ErrorsService){
        super(router, http, errorService);
    }
    getUsers(): Observable<User[]> {
        if (!this.users) {
            this.users = this.fillList();
        }
        return of(this.users);
    }
    addUser(user: User) {
        this.users.push(user);
    }
    fillList(): User[] {
        return [
            new User({
                'id': 1,
                'usuario': 'cgreystoke0',
                'nome': 'Craggie Greystoke',
                'senha': 'b3472ead94ec05483e3d2f276978fbed',
                'role': 0
            }),
            new User({
                'id': 2,
                'usuario': 'vhenworth1',
                'nome': 'Vale Henworth',
                'senha': 'fc9c326f145a03e50ec20a9925855987',
                'role': 0
            }),
            new User({
                'id': 3,
                'usuario': 'jleadbeater2',
                'nome': 'Jackquelin Leadbeater',
                'senha': '13aab262db844b93c843a9fa0712afde',
                'role': 0
            }),
            new User({
                'id': 4,
                'usuario': 'nemmens3',
                'nome': 'Norah Emmens',
                'senha': 'f671eeedf0700a787be998e49ed54b44',
                'role': 0
            }),
            new User({
                'id': 5,
                'usuario': 'aknapman4',
                'nome': 'Adoree Knapman',
                'senha': '375c0d66d2b55e46986b42b1cef70070',
                'role': 0
            }),
            new User({
                'id': 6,
                'usuario': 'tgrundle5',
                'nome': 'Teddi Grundle',
                'senha': 'b1d30147f73c56b3fdd6323910ae63ef',
                'role': 0
            }),
            new User({
                'id': 7,
                'usuario': 'khearty6',
                'nome': 'Krispin Hearty',
                'senha': '583d88ad18e1d59ac2aa73e63040d30b',
                'role': 0
            }),
            new User({
                'id': 8,
                'usuario': 'kgroger7',
                'nome': 'Karrie Groger',
                'senha': '27e3c6f11136a6daf3a01e6bf7004d93',
                'role': 0
            }),
            new User({
                'id': 9,
                'usuario': 'mbru8',
                'nome': 'Marleen Bru',
                'senha': '3c903f81e181091f9791964a9df6a477',
                'role': 1
            }),
            new User({
                'id': 10,
                'usuario': 'eclague9',
                'nome': 'Erinna Clague',
                'senha': 'c43e1512c33e119d00f6b7eee0493e52',
                'role': 0
            }),
            new User({
                'id': 11,
                'usuario': 'pbirtleya',
                'nome': 'Pate Birtley',
                'senha': 'a0db13384172f91d6b48d605c4628040',
                'role': 0
            }),
            new User({
                'id': 12,
                'usuario': 'hgilfetherb',
                'nome': 'Hattie Gilfether',
                'senha': '159e4442726b66f4bf2d026f77237608',
                'role': 1
            }),
            new User({
                'id': 13,
                'usuario': 'wnuzzettic',
                'nome': 'Waylin Nuzzetti',
                'senha': '5292fcb9f77027dd66c20ea509ebbd8e',
                'role': 0
            }),
            new User({
                'id': 14,
                'usuario': 'hmerkeld',
                'nome': 'Howie Merkel',
                'senha': '43a4e21ac75856be6cea408235afc448',
                'role': 0
            }),
            new User({
                'id': 15,
                'usuario': 'mtosdevine',
                'nome': 'Modesta Tosdevin',
                'senha': '7583aed1edb9ccfc38c3f9727fc8f015',
                'role': 0
            }),
            new User({
                'id': 16,
                'usuario': 'keckartf',
                'nome': 'Katha Eckart',
                'senha': '74093372674999b15cc0ce5642ee3976',
                'role': 0
            }),
            new User({
                'id': 17,
                'usuario': 'jchastanetg',
                'nome': 'Jaclin Chastanet',
                'senha': '1c0df0bd8b5762ec8aa3d6747fe3e049',
                'role': 0
            }),
            new User({
                'id': 18,
                'usuario': 'gheadingsh',
                'nome': 'Germaine Headings',
                'senha': '4f7c198f2225f07d8bcb9c1095fd6d15',
                'role': 0
            }),
            new User({
                'id': 19,
                'usuario': 'eselburni',
                'nome': 'Egbert Selburn',
                'senha': '9d19c134710d3107db0ce8a9e0b1226b',
                'role': 0
            }),
            new User({
                'id': 20,
                'usuario': 'jpyrtonj',
                'nome': 'Jefferson Pyrton',
                'senha': '2844e775f45bfda691add2c80d654693',
                'role': 0
            }),
            new User({
                'id': 21,
                'usuario': 'cfannerk',
                'nome': 'Carroll Fanner',
                'senha': '8407c3c76da9f5ddd424f93538c31814',
                'role': 1
            }),
            new User({
                'id': 22,
                'usuario': 'cillyesl',
                'nome': 'Clerc Illyes',
                'senha': 'cc6e95b9ad6dfe5a13a049ffe84e51a4',
                'role': 0
            }),
            new User({
                'id': 23,
                'usuario': 'nsewellm',
                'nome': 'Nissie Sewell',
                'senha': '2ed091dd95777fda462386b67a57048b',
                'role': 0
            }),
            new User({
                'id': 24,
                'usuario': 'wseamarken',
                'nome': 'Wilmar Seamarke',
                'senha': 'f1da4786893c82e65993a44fdc9627bb',
                'role': 0
            }),
            new User({
                'id': 25,
                'usuario': 'sbeveredgeo',
                'nome': 'Sigfrid Beveredge',
                'senha': '93af61c58a5ae0f19893a3925f99fbe7',
                'role': 0
            }),
            new User({
                'id': 26,
                'usuario': 'aklussp',
                'nome': 'Alexina Kluss',
                'senha': '3230322e30b478774f2708adea6decd2',
                'role': 0
            }),
            new User({
                'id': 27,
                'usuario': 'sovesenq',
                'nome': 'Sinclare Ovesen',
                'senha': '34f6ad60e7b45f118b764b58c6728065',
                'role': 0
            }),
            new User({
                'id': 28,
                'usuario': 'fsmeetonr',
                'nome': 'Florida Smeeton',
                'senha': '45ef2245b9596bca8d52ac16632a4ae5',
                'role': 1
            }),
            new User({
                'id': 29,
                'usuario': 'ghostons',
                'nome': 'Giffy Hoston',
                'senha': 'd030f9897b27f953b049d140dda4145c',
                'role': 0
            }),
            new User({
                'id': 30,
                'usuario': 'cschimmangt',
                'nome': 'Claudette Schimmang',
                'senha': '0e12d6635c2f4af421034d1434d90810',
                'role': 0
            }),
            new User({
                'id': 31,
                'usuario': 'icontiu',
                'nome': 'Iormina Conti',
                'senha': '5e9f4aa143ab82051c1d9475d53b4186',
                'role': 0
            }),
            new User({
                'id': 32,
                'usuario': 'sfossettv',
                'nome': 'Sallyanne Fossett',
                'senha': 'f367e1399a968bff2207d604f99bfd71',
                'role': 1
            }),
            new User({
                'id': 33,
                'usuario': 'dmacneilleyw',
                'nome': 'Debora MacNeilley',
                'senha': '3b70900620ab707f206275ca451cbb59',
                'role': 0
            }),
            new User({
                'id': 34,
                'usuario': 'dlex',
                'nome': 'Donalt Le Claire',
                'senha': '7fee8ef4a0d238d82c9e3ed623f06871',
                'role': 0
            }),
            new User({
                'id': 35,
                'usuario': 'gmaplesdeny',
                'nome': 'Gisela Maplesden',
                'senha': 'bea6835e1e6d1070e3e2b128ffc8c74d',
                'role': 0
            }),
            new User({
                'id': 36,
                'usuario': 'hrookledgez',
                'nome': 'Hilary Rookledge',
                'senha': '37e0eaf035fb764aa6db6090df6a0f6e',
                'role': 1
            }),
            new User({
                'id': 37,
                'usuario': 'lhane10',
                'nome': 'Linn Hane',
                'senha': 'ee12a6f5b429a05585b21fedff31473f',
                'role': 1
            }),
            new User({
                'id': 38,
                'usuario': 'bglencrosche11',
                'nome': 'Brendin Glencrosche',
                'senha': '556604618b77e7852c3a4c182725c2b2',
                'role': 1
            }),
            new User({
                'id': 39,
                'usuario': 'cramsey12',
                'nome': 'Cheri Ramsey',
                'senha': 'f763e78e0c471d8ad9d8524ea5251210',
                'role': 1
            }),
            new User({
                'id': 40,
                'usuario': 'jcodlin13',
                'nome': 'Jody Codlin',
                'senha': '3901985f6d7ba6b72435f4e133cd6ef9',
                'role': 0
            }),
            new User({
                'id': 41,
                'usuario': 'ajandel14',
                'nome': 'Alia Jandel',
                'senha': '3e1e19d69b634c2f4f7316afcc89b18a',
                'role': 0
            }),
            new User({
                'id': 42,
                'usuario': 'cwork15',
                'nome': 'Chalmers Work',
                'senha': 'df28ae5047d369c1f7b119d3e7269a45',
                'role': 1
            }),
            new User({
                'id': 43,
                'usuario': 'kbeakes16',
                'nome': 'Keenan Beakes',
                'senha': '1801ece98269cf31973073a74ed44db2',
                'role': 0
            }),
            new User({
                'id': 44,
                'usuario': 'bclubbe17',
                'nome': 'Barr Clubbe',
                'senha': 'e27e1206b807db276df5185caf2a0845',
                'role': 0
            }),
            new User({
                'id': 45,
                'usuario': 'cmapowder18',
                'nome': 'Carlita Mapowder',
                'senha': '0b634ba68712e5b25e32d15ae43aea9d',
                'role': 1
            }),
            new User({
                'id': 46,
                'usuario': 'bgibben19',
                'nome': 'Brandise Gibben',
                'senha': '5a6d3b431e7b720c905145293b0c044f',
                'role': 0
            }),
            new User({
                'id': 47,
                'usuario': 'nblanchflower1a',
                'nome': 'Neddy Blanchflower',
                'senha': '0269bbb11f2631d9429b46a5402980cf',
                'role': 1
            }),
            new User({
                'id': 48,
                'usuario': 'jfarnworth1b',
                'nome': 'Jesse Farnworth',
                'senha': '804d1b0d178af3aaf5b2a32d62919d66',
                'role': 0
            }),
            new User({
                'id': 49,
                'usuario': 'eregelous1c',
                'nome': 'Erastus Regelous',
                'senha': 'bb8e89e3acdb69b06298d6ff4a2910c8',
                'role': 0
            }),
            new User({
                'id': 50,
                'usuario': 'vdonavan1d',
                'nome': 'Vivian Donavan',
                'senha': 'b4ba4da749819e1c3ecdeeab6b38eec0',
                'role': 0
            }),
            new User({
                'id': 51,
                'usuario': 'bcuttin1e',
                'nome': 'Bevon Cuttin',
                'senha': 'f7d1e2c54a6ab88044b378f0d300da67',
                'role': 0
            }),
            new User({
                'id': 52,
                'usuario': 'igarfield1f',
                'nome': 'Iorgos Garfield',
                'senha': 'd24dd4f54a91d4aaa3835df88350707e',
                'role': 1
            }),
            new User({
                'id': 53,
                'usuario': 'ngammage1g',
                'nome': 'Nell Gammage',
                'senha': 'ebaae0985ef62281f3a201c8f8452a36',
                'role': 1
            }),
            new User({
                'id': 54,
                'usuario': 'fbolgar1h',
                'nome': 'Frank Bolgar',
                'senha': '93b2a7fcade59a9d49b43bfe9351e596',
                'role': 0
            }),
            new User({
                'id': 55,
                'usuario': 'dmynard1i',
                'nome': 'Dalton Mynard',
                'senha': '4fa2fc3a9359488e3fdfb776d9f76a5c',
                'role': 0
            }),
            new User({
                'id': 56,
                'usuario': 'rgerger1j',
                'nome': 'Rowland Gerger',
                'senha': '3c90f8e9ff44aad8270d745acf085a5d',
                'role': 0
            }),
            new User({
                'id': 57,
                'usuario': 'osanford1k',
                'nome': 'Oralia Sanford',
                'senha': '8859a31a8dece25cfaf3613d637e061f',
                'role': 0
            }),
            new User({
                'id': 58,
                'usuario': 'sbroek1l',
                'nome': 'Silvano Broek',
                'senha': 'affa9c1e605e3e832496aa070b8fc7da',
                'role': 0
            }),
            new User({
                'id': 59,
                'usuario': 'tflye1m',
                'nome': 'Templeton Flye',
                'senha': 'f9e2c4b0ba1996f0debabfa37e220914',
                'role': 0
            }),
            new User({
                'id': 60,
                'usuario': 'ebeyne1n',
                'nome': 'Elmo Beyne',
                'senha': '21e06cd7b206f2b3e534d40c672aa55f',
                'role': 1
            }),
            new User({
                'id': 61,
                'usuario': 'oralphs1o',
                'nome': 'Olin Ralphs',
                'senha': 'fa3616b4f159d0381b79780039fd0925',
                'role': 1
            }),
            new User({
                'id': 62,
                'usuario': 'drosenblath1p',
                'nome': 'Doris Rosenblath',
                'senha': 'd338e92d60b92f6565912d14ecbcb881',
                'role': 0
            }),
            new User({
                'id': 63,
                'usuario': 'tlansberry1q',
                'nome': 'Twila Lansberry',
                'senha': '8a469edbb56312c6f809bc5fdbb36587',
                'role': 1
            }),
            new User({
                'id': 64,
                'usuario': 'mzapatero1r',
                'nome': 'Margeaux Zapatero',
                'senha': '694666593a0461e8231fa92e50d29412',
                'role': 0
            }),
            new User({
                'id': 65,
                'usuario': 'rmackness1s',
                'nome': 'Rita Mackness',
                'senha': 'e0a43978b9db14e65ab44e9d628ed877',
                'role': 0
            }),
            new User({
                'id': 66,
                'usuario': 'aobradden1t',
                // tslint:disable-next-line:quotemark
                'nome': "Aigneis O'Bradden",
                'senha': 'ace7e01e458fc3a82760f28163ee412b',
                'role': 0
            }),
            new User({
                'id': 67,
                'usuario': 'gfinlater1u',
                'nome': 'Grete Finlater',
                'senha': '761c90e277b1c9fe1f7a255bb7c81da5',
                'role': 1
            }),
            new User({
                'id': 68,
                'usuario': 'phowden1v',
                'nome': 'Pancho Howden',
                'senha': 'a89d367d243e1cda5414de322eac268f',
                'role': 0
            }),
            new User({
                'id': 69,
                'usuario': 'ltoffoloni1w',
                'nome': 'Laryssa Toffoloni',
                'senha': '590f10a6bc4cedab330bebc1f60cc339',
                'role': 0
            }),
            new User({
                'id': 70,
                'usuario': 'sweetch1x',
                'nome': 'Sally Weetch',
                'senha': '5a70ba46d5d8b598f9fd71d834b1a214',
                'role': 1
            }),
            new User({
                'id': 71,
                'usuario': 'cbenson1y',
                'nome': 'Courtney Benson',
                'senha': '2ba1430e9073571cc8fc9868d6647c12',
                'role': 0
            }),
            new User({
                'id': 72,
                'usuario': 'bantonsson1z',
                'nome': 'Barney Antonsson',
                'senha': '173fce22f681697ddadba2bc86e2cbef',
                'role': 0
            }),
            new User({
                'id': 73,
                'usuario': 'agravenell20',
                'nome': 'Almeta Gravenell',
                'senha': '0a83f0f059c54734f7b036267c4e9054',
                'role': 0
            }),
            new User({
                'id': 74,
                'usuario': 'mbartoshevich21',
                'nome': 'Merrilee Bartoshevich',
                'senha': '5aa7b2968e06f7288ab83020c80e9296',
                'role': 1
            }),
            new User({
                'id': 75,
                'usuario': 'gdoxey22',
                'nome': 'Guillaume Doxey',
                'senha': '3407a036efe2c470118e403ea02e9197',
                'role': 0
            }),
            new User({
                'id': 76,
                'usuario': 'ihane23',
                'nome': 'Isabel Hane',
                'senha': '930dc9ed00c06ee539d2980b837d72e2',
                'role': 0
            }),
            new User({
                'id': 77,
                'usuario': 'qgalvan24',
                'nome': 'Querida Galvan',
                'senha': 'c07ab659cbcdb32bf76025af134ccae9',
                'role': 0
            }),
            new User({
                'id': 78,
                'usuario': 'ldinsdale25',
                'nome': 'Lizette Dinsdale',
                'senha': '2759ed84cb1148481e44bae4886d6f98',
                'role': 1
            }),
            new User({
                'id': 79,
                'usuario': 'vtrazzi26',
                'nome': 'Vance Trazzi',
                'senha': 'd007c4b34ce333dc76fe4d12ff56c21e',
                'role': 0
            }),
            new User({
                'id': 80,
                'usuario': 'eborthwick27',
                'nome': 'Emylee Borthwick',
                'senha': 'cbea85ac1ad9ae23479d537c64ec82e1',
                'role': 0
            }),
            new User({
                'id': 81,
                'usuario': 'epeeke28',
                'nome': 'Eadmund Peeke',
                'senha': '122f46df22a5dbec6201d2f8a4359054',
                'role': 0
            }),
            new User({
                'id': 82,
                'usuario': 'lmaber29',
                'nome': 'Leigh Maber',
                'senha': 'cb5ae16eadf28f9ab57bac889d005931',
                'role': 0
            }),
            new User({
                'id': 83,
                'usuario': 'fhartil2a',
                'nome': 'Faustina Hartil',
                'senha': '28010bcb4176c8e4006ee11934ba56a1',
                'role': 0
            }),
            new User({
                'id': 84,
                'usuario': 'aizakov2b',
                'nome': 'Aloisia Izakov',
                'senha': '8794e4a48e961805e6baa76fe763a58a',
                'role': 0
            }),
            new User({
                'id': 85,
                'usuario': 'bbatalle2c',
                'nome': 'Bathsheba Batalle',
                'senha': 'af9f3fb0c4c8c37b5e8237354adbf9d7',
                'role': 1
            }),
            new User({
                'id': 86,
                'usuario': 'bchene2d',
                'nome': 'Bobbe Chene',
                'senha': '4fa1183bab3ddadb3710cc9e16a72766',
                'role': 0
            }),
            new User({
                'id': 87,
                'usuario': 'vbrimfield2e',
                'nome': 'Vida Brimfield',
                'senha': '3a3c3c6f6251b82ff45e39e2b30ca6d9',
                'role': 0
            }),
            new User({
                'id': 88,
                'usuario': 'schampley2f',
                'nome': 'Sybille Champley',
                'senha': '46167332896a42e556e6d535e34ee98b',
                'role': 0
            }),
            new User({
                'id': 89,
                'usuario': 'lfoxwell2g',
                'nome': 'Loralyn Foxwell',
                'senha': '6b3660bb46cf94da21311461a908ee97',
                'role': 0
            }),
            new User({
                'id': 90,
                'usuario': 'acrotty2h',
                'nome': 'Atlanta Crotty',
                'senha': 'ea5a413d0dbd9329457ae8806c3070a8',
                'role': 1
            }),
            new User({
                'id': 91,
                'usuario': 'dadanet2i',
                'nome': 'Darryl Adanet',
                'senha': 'df2e9d8df7a6947e327641574b027c66',
                'role': 0
            }),
            new User({
                'id': 92,
                'usuario': 'jbailiss2j',
                'nome': 'Jacinthe Bailiss',
                'senha': '2a7ee7b5e34c3b2056f1fb4708c4883f',
                'role': 0
            }),
            new User({
                'id': 93,
                'usuario': 'cvearncomb2k',
                'nome': 'Cahra Vearncomb',
                'senha': '4185c895ad158b7b2e541afa7ee5e19c',
                'role': 0
            }),
            new User({
                'id': 94,
                'usuario': 'cpercy2l',
                'nome': 'Chris Percy',
                'senha': '6efacdc26d0104699404be2231e9af64',
                'role': 1
            }),
            new User({
                'id': 95,
                'usuario': 'ddraye2m',
                'nome': 'Demetra Draye',
                'senha': '21bcbe91a673a8289183c3b84c876b7f',
                'role': 0
            }),
            new User({
                'id': 96,
                'usuario': 'dmclugaish2n',
                'nome': 'Dmitri McLugaish',
                'senha': '9c31514c556cd0ae71a217f621bb379b',
                'role': 0
            }),
            new User({
                'id': 97,
                'usuario': 'mhonsch2o',
                'nome': 'Maison Honsch',
                'senha': '806d35e5db387d22938f6c85be86c0ea',
                'role': 0
            }),
            new User({
                'id': 98,
                'usuario': 'lslimm2p',
                'nome': 'Laurie Slimm',
                'senha': '54be61fca2edcad967c604ead91fca86',
                'role': 1
            }),
            new User({
                'id': 99,
                'usuario': 'usuario',
                'nome': 'Gretal Baudon',
                'senha': 'ee11cbb19052e40b07aac0ca060c23ee',
                'role': 0
            }),
            new User({
                'id': 100,
                'usuario': 'admin',
                'nome': 'Administrador',
                'senha': '21232f297a57a5a743894a0e4a801fc3',
                'role': 1
            })];
    }
}
