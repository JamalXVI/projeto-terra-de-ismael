import { Injectable } from '@angular/core';

import { User } from './user.model';

@Injectable({ providedIn: 'root' })
export class UserService {
    private users: User[];
    constructor() {
        this.users = this.fillList();
    }
    gerUsers(): User[] {
        return this.users;
    }
    addUser(user: User) {
        this.users.push(user);
    }
    fillList(): User[] {
        return [
            new User({
                'id': 1,
                'user': 'cgreystoke0',
                'name': 'Craggie Greystoke',
                'password': 'b3472ead94ec05483e3d2f276978fbed',
                'role': 0
            }),
            new User({
                'id': 2,
                'user': 'vhenworth1',
                'name': 'Vale Henworth',
                'password': 'fc9c326f145a03e50ec20a9925855987',
                'role': 0
            }),
            new User({
                'id': 3,
                'user': 'jleadbeater2',
                'name': 'Jackquelin Leadbeater',
                'password': '13aab262db844b93c843a9fa0712afde',
                'role': 0
            }),
            new User({
                'id': 4,
                'user': 'nemmens3',
                'name': 'Norah Emmens',
                'password': 'f671eeedf0700a787be998e49ed54b44',
                'role': 0
            }),
            new User({
                'id': 5,
                'user': 'aknapman4',
                'name': 'Adoree Knapman',
                'password': '375c0d66d2b55e46986b42b1cef70070',
                'role': 0
            }),
            new User({
                'id': 6,
                'user': 'tgrundle5',
                'name': 'Teddi Grundle',
                'password': 'b1d30147f73c56b3fdd6323910ae63ef',
                'role': 0
            }),
            new User({
                'id': 7,
                'user': 'khearty6',
                'name': 'Krispin Hearty',
                'password': '583d88ad18e1d59ac2aa73e63040d30b',
                'role': 0
            }),
            new User({
                'id': 8,
                'user': 'kgroger7',
                'name': 'Karrie Groger',
                'password': '27e3c6f11136a6daf3a01e6bf7004d93',
                'role': 0
            }),
            new User({
                'id': 9,
                'user': 'mbru8',
                'name': 'Marleen Bru',
                'password': '3c903f81e181091f9791964a9df6a477',
                'role': 1
            }),
            new User({
                'id': 10,
                'user': 'eclague9',
                'name': 'Erinna Clague',
                'password': 'c43e1512c33e119d00f6b7eee0493e52',
                'role': 0
            }),
            new User({
                'id': 11,
                'user': 'pbirtleya',
                'name': 'Pate Birtley',
                'password': 'a0db13384172f91d6b48d605c4628040',
                'role': 0
            }),
            new User({
                'id': 12,
                'user': 'hgilfetherb',
                'name': 'Hattie Gilfether',
                'password': '159e4442726b66f4bf2d026f77237608',
                'role': 1
            }),
            new User({
                'id': 13,
                'user': 'wnuzzettic',
                'name': 'Waylin Nuzzetti',
                'password': '5292fcb9f77027dd66c20ea509ebbd8e',
                'role': 0
            }),
            new User({
                'id': 14,
                'user': 'hmerkeld',
                'name': 'Howie Merkel',
                'password': '43a4e21ac75856be6cea408235afc448',
                'role': 0
            }),
            new User({
                'id': 15,
                'user': 'mtosdevine',
                'name': 'Modesta Tosdevin',
                'password': '7583aed1edb9ccfc38c3f9727fc8f015',
                'role': 0
            }),
            new User({
                'id': 16,
                'user': 'keckartf',
                'name': 'Katha Eckart',
                'password': '74093372674999b15cc0ce5642ee3976',
                'role': 0
            }),
            new User({
                'id': 17,
                'user': 'jchastanetg',
                'name': 'Jaclin Chastanet',
                'password': '1c0df0bd8b5762ec8aa3d6747fe3e049',
                'role': 0
            }),
            new User({
                'id': 18,
                'user': 'gheadingsh',
                'name': 'Germaine Headings',
                'password': '4f7c198f2225f07d8bcb9c1095fd6d15',
                'role': 0
            }),
            new User({
                'id': 19,
                'user': 'eselburni',
                'name': 'Egbert Selburn',
                'password': '9d19c134710d3107db0ce8a9e0b1226b',
                'role': 0
            }),
            new User({
                'id': 20,
                'user': 'jpyrtonj',
                'name': 'Jefferson Pyrton',
                'password': '2844e775f45bfda691add2c80d654693',
                'role': 0
            }),
            new User({
                'id': 21,
                'user': 'cfannerk',
                'name': 'Carroll Fanner',
                'password': '8407c3c76da9f5ddd424f93538c31814',
                'role': 1
            }),
            new User({
                'id': 22,
                'user': 'cillyesl',
                'name': 'Clerc Illyes',
                'password': 'cc6e95b9ad6dfe5a13a049ffe84e51a4',
                'role': 0
            }),
            new User({
                'id': 23,
                'user': 'nsewellm',
                'name': 'Nissie Sewell',
                'password': '2ed091dd95777fda462386b67a57048b',
                'role': 0
            }),
            new User({
                'id': 24,
                'user': 'wseamarken',
                'name': 'Wilmar Seamarke',
                'password': 'f1da4786893c82e65993a44fdc9627bb',
                'role': 0
            }),
            new User({
                'id': 25,
                'user': 'sbeveredgeo',
                'name': 'Sigfrid Beveredge',
                'password': '93af61c58a5ae0f19893a3925f99fbe7',
                'role': 0
            }),
            new User({
                'id': 26,
                'user': 'aklussp',
                'name': 'Alexina Kluss',
                'password': '3230322e30b478774f2708adea6decd2',
                'role': 0
            }),
            new User({
                'id': 27,
                'user': 'sovesenq',
                'name': 'Sinclare Ovesen',
                'password': '34f6ad60e7b45f118b764b58c6728065',
                'role': 0
            }),
            new User({
                'id': 28,
                'user': 'fsmeetonr',
                'name': 'Florida Smeeton',
                'password': '45ef2245b9596bca8d52ac16632a4ae5',
                'role': 1
            }),
            new User({
                'id': 29,
                'user': 'ghostons',
                'name': 'Giffy Hoston',
                'password': 'd030f9897b27f953b049d140dda4145c',
                'role': 0
            }),
            new User({
                'id': 30,
                'user': 'cschimmangt',
                'name': 'Claudette Schimmang',
                'password': '0e12d6635c2f4af421034d1434d90810',
                'role': 0
            }),
            new User({
                'id': 31,
                'user': 'icontiu',
                'name': 'Iormina Conti',
                'password': '5e9f4aa143ab82051c1d9475d53b4186',
                'role': 0
            }),
            new User({
                'id': 32,
                'user': 'sfossettv',
                'name': 'Sallyanne Fossett',
                'password': 'f367e1399a968bff2207d604f99bfd71',
                'role': 1
            }),
            new User({
                'id': 33,
                'user': 'dmacneilleyw',
                'name': 'Debora MacNeilley',
                'password': '3b70900620ab707f206275ca451cbb59',
                'role': 0
            }),
            new User({
                'id': 34,
                'user': 'dlex',
                'name': 'Donalt Le Claire',
                'password': '7fee8ef4a0d238d82c9e3ed623f06871',
                'role': 0
            }),
            new User({
                'id': 35,
                'user': 'gmaplesdeny',
                'name': 'Gisela Maplesden',
                'password': 'bea6835e1e6d1070e3e2b128ffc8c74d',
                'role': 0
            }),
            new User({
                'id': 36,
                'user': 'hrookledgez',
                'name': 'Hilary Rookledge',
                'password': '37e0eaf035fb764aa6db6090df6a0f6e',
                'role': 1
            }),
            new User({
                'id': 37,
                'user': 'lhane10',
                'name': 'Linn Hane',
                'password': 'ee12a6f5b429a05585b21fedff31473f',
                'role': 1
            }),
            new User({
                'id': 38,
                'user': 'bglencrosche11',
                'name': 'Brendin Glencrosche',
                'password': '556604618b77e7852c3a4c182725c2b2',
                'role': 1
            }),
            new User({
                'id': 39,
                'user': 'cramsey12',
                'name': 'Cheri Ramsey',
                'password': 'f763e78e0c471d8ad9d8524ea5251210',
                'role': 1
            }),
            new User({
                'id': 40,
                'user': 'jcodlin13',
                'name': 'Jody Codlin',
                'password': '3901985f6d7ba6b72435f4e133cd6ef9',
                'role': 0
            }),
            new User({
                'id': 41,
                'user': 'ajandel14',
                'name': 'Alia Jandel',
                'password': '3e1e19d69b634c2f4f7316afcc89b18a',
                'role': 0
            }),
            new User({
                'id': 42,
                'user': 'cwork15',
                'name': 'Chalmers Work',
                'password': 'df28ae5047d369c1f7b119d3e7269a45',
                'role': 1
            }),
            new User({
                'id': 43,
                'user': 'kbeakes16',
                'name': 'Keenan Beakes',
                'password': '1801ece98269cf31973073a74ed44db2',
                'role': 0
            }),
            new User({
                'id': 44,
                'user': 'bclubbe17',
                'name': 'Barr Clubbe',
                'password': 'e27e1206b807db276df5185caf2a0845',
                'role': 0
            }),
            new User({
                'id': 45,
                'user': 'cmapowder18',
                'name': 'Carlita Mapowder',
                'password': '0b634ba68712e5b25e32d15ae43aea9d',
                'role': 1
            }),
            new User({
                'id': 46,
                'user': 'bgibben19',
                'name': 'Brandise Gibben',
                'password': '5a6d3b431e7b720c905145293b0c044f',
                'role': 0
            }),
            new User({
                'id': 47,
                'user': 'nblanchflower1a',
                'name': 'Neddy Blanchflower',
                'password': '0269bbb11f2631d9429b46a5402980cf',
                'role': 1
            }),
            new User({
                'id': 48,
                'user': 'jfarnworth1b',
                'name': 'Jesse Farnworth',
                'password': '804d1b0d178af3aaf5b2a32d62919d66',
                'role': 0
            }),
            new User({
                'id': 49,
                'user': 'eregelous1c',
                'name': 'Erastus Regelous',
                'password': 'bb8e89e3acdb69b06298d6ff4a2910c8',
                'role': 0
            }),
            new User({
                'id': 50,
                'user': 'vdonavan1d',
                'name': 'Vivian Donavan',
                'password': 'b4ba4da749819e1c3ecdeeab6b38eec0',
                'role': 0
            }),
            new User({
                'id': 51,
                'user': 'bcuttin1e',
                'name': 'Bevon Cuttin',
                'password': 'f7d1e2c54a6ab88044b378f0d300da67',
                'role': 0
            }),
            new User({
                'id': 52,
                'user': 'igarfield1f',
                'name': 'Iorgos Garfield',
                'password': 'd24dd4f54a91d4aaa3835df88350707e',
                'role': 1
            }),
            new User({
                'id': 53,
                'user': 'ngammage1g',
                'name': 'Nell Gammage',
                'password': 'ebaae0985ef62281f3a201c8f8452a36',
                'role': 1
            }),
            new User({
                'id': 54,
                'user': 'fbolgar1h',
                'name': 'Frank Bolgar',
                'password': '93b2a7fcade59a9d49b43bfe9351e596',
                'role': 0
            }),
            new User({
                'id': 55,
                'user': 'dmynard1i',
                'name': 'Dalton Mynard',
                'password': '4fa2fc3a9359488e3fdfb776d9f76a5c',
                'role': 0
            }),
            new User({
                'id': 56,
                'user': 'rgerger1j',
                'name': 'Rowland Gerger',
                'password': '3c90f8e9ff44aad8270d745acf085a5d',
                'role': 0
            }),
            new User({
                'id': 57,
                'user': 'osanford1k',
                'name': 'Oralia Sanford',
                'password': '8859a31a8dece25cfaf3613d637e061f',
                'role': 0
            }),
            new User({
                'id': 58,
                'user': 'sbroek1l',
                'name': 'Silvano Broek',
                'password': 'affa9c1e605e3e832496aa070b8fc7da',
                'role': 0
            }),
            new User({
                'id': 59,
                'user': 'tflye1m',
                'name': 'Templeton Flye',
                'password': 'f9e2c4b0ba1996f0debabfa37e220914',
                'role': 0
            }),
            new User({
                'id': 60,
                'user': 'ebeyne1n',
                'name': 'Elmo Beyne',
                'password': '21e06cd7b206f2b3e534d40c672aa55f',
                'role': 1
            }),
            new User({
                'id': 61,
                'user': 'oralphs1o',
                'name': 'Olin Ralphs',
                'password': 'fa3616b4f159d0381b79780039fd0925',
                'role': 1
            }),
            new User({
                'id': 62,
                'user': 'drosenblath1p',
                'name': 'Doris Rosenblath',
                'password': 'd338e92d60b92f6565912d14ecbcb881',
                'role': 0
            }),
            new User({
                'id': 63,
                'user': 'tlansberry1q',
                'name': 'Twila Lansberry',
                'password': '8a469edbb56312c6f809bc5fdbb36587',
                'role': 1
            }),
            new User({
                'id': 64,
                'user': 'mzapatero1r',
                'name': 'Margeaux Zapatero',
                'password': '694666593a0461e8231fa92e50d29412',
                'role': 0
            }),
            new User({
                'id': 65,
                'user': 'rmackness1s',
                'name': 'Rita Mackness',
                'password': 'e0a43978b9db14e65ab44e9d628ed877',
                'role': 0
            }),
            new User({
                'id': 66,
                'user': 'aobradden1t',
                // tslint:disable-next-line:quotemark
                'name': "Aigneis O'Bradden",
                'password': 'ace7e01e458fc3a82760f28163ee412b',
                'role': 0
            }),
            new User({
                'id': 67,
                'user': 'gfinlater1u',
                'name': 'Grete Finlater',
                'password': '761c90e277b1c9fe1f7a255bb7c81da5',
                'role': 1
            }),
            new User({
                'id': 68,
                'user': 'phowden1v',
                'name': 'Pancho Howden',
                'password': 'a89d367d243e1cda5414de322eac268f',
                'role': 0
            }),
            new User({
                'id': 69,
                'user': 'ltoffoloni1w',
                'name': 'Laryssa Toffoloni',
                'password': '590f10a6bc4cedab330bebc1f60cc339',
                'role': 0
            }),
            new User({
                'id': 70,
                'user': 'sweetch1x',
                'name': 'Sally Weetch',
                'password': '5a70ba46d5d8b598f9fd71d834b1a214',
                'role': 1
            }),
            new User({
                'id': 71,
                'user': 'cbenson1y',
                'name': 'Courtney Benson',
                'password': '2ba1430e9073571cc8fc9868d6647c12',
                'role': 0
            }),
            new User({
                'id': 72,
                'user': 'bantonsson1z',
                'name': 'Barney Antonsson',
                'password': '173fce22f681697ddadba2bc86e2cbef',
                'role': 0
            }),
            new User({
                'id': 73,
                'user': 'agravenell20',
                'name': 'Almeta Gravenell',
                'password': '0a83f0f059c54734f7b036267c4e9054',
                'role': 0
            }),
            new User({
                'id': 74,
                'user': 'mbartoshevich21',
                'name': 'Merrilee Bartoshevich',
                'password': '5aa7b2968e06f7288ab83020c80e9296',
                'role': 1
            }),
            new User({
                'id': 75,
                'user': 'gdoxey22',
                'name': 'Guillaume Doxey',
                'password': '3407a036efe2c470118e403ea02e9197',
                'role': 0
            }),
            new User({
                'id': 76,
                'user': 'ihane23',
                'name': 'Isabel Hane',
                'password': '930dc9ed00c06ee539d2980b837d72e2',
                'role': 0
            }),
            new User({
                'id': 77,
                'user': 'qgalvan24',
                'name': 'Querida Galvan',
                'password': 'c07ab659cbcdb32bf76025af134ccae9',
                'role': 0
            }),
            new User({
                'id': 78,
                'user': 'ldinsdale25',
                'name': 'Lizette Dinsdale',
                'password': '2759ed84cb1148481e44bae4886d6f98',
                'role': 1
            }),
            new User({
                'id': 79,
                'user': 'vtrazzi26',
                'name': 'Vance Trazzi',
                'password': 'd007c4b34ce333dc76fe4d12ff56c21e',
                'role': 0
            }),
            new User({
                'id': 80,
                'user': 'eborthwick27',
                'name': 'Emylee Borthwick',
                'password': 'cbea85ac1ad9ae23479d537c64ec82e1',
                'role': 0
            }),
            new User({
                'id': 81,
                'user': 'epeeke28',
                'name': 'Eadmund Peeke',
                'password': '122f46df22a5dbec6201d2f8a4359054',
                'role': 0
            }),
            new User({
                'id': 82,
                'user': 'lmaber29',
                'name': 'Leigh Maber',
                'password': 'cb5ae16eadf28f9ab57bac889d005931',
                'role': 0
            }),
            new User({
                'id': 83,
                'user': 'fhartil2a',
                'name': 'Faustina Hartil',
                'password': '28010bcb4176c8e4006ee11934ba56a1',
                'role': 0
            }),
            new User({
                'id': 84,
                'user': 'aizakov2b',
                'name': 'Aloisia Izakov',
                'password': '8794e4a48e961805e6baa76fe763a58a',
                'role': 0
            }),
            new User({
                'id': 85,
                'user': 'bbatalle2c',
                'name': 'Bathsheba Batalle',
                'password': 'af9f3fb0c4c8c37b5e8237354adbf9d7',
                'role': 1
            }),
            new User({
                'id': 86,
                'user': 'bchene2d',
                'name': 'Bobbe Chene',
                'password': '4fa1183bab3ddadb3710cc9e16a72766',
                'role': 0
            }),
            new User({
                'id': 87,
                'user': 'vbrimfield2e',
                'name': 'Vida Brimfield',
                'password': '3a3c3c6f6251b82ff45e39e2b30ca6d9',
                'role': 0
            }),
            new User({
                'id': 88,
                'user': 'schampley2f',
                'name': 'Sybille Champley',
                'password': '46167332896a42e556e6d535e34ee98b',
                'role': 0
            }),
            new User({
                'id': 89,
                'user': 'lfoxwell2g',
                'name': 'Loralyn Foxwell',
                'password': '6b3660bb46cf94da21311461a908ee97',
                'role': 0
            }),
            new User({
                'id': 90,
                'user': 'acrotty2h',
                'name': 'Atlanta Crotty',
                'password': 'ea5a413d0dbd9329457ae8806c3070a8',
                'role': 1
            }),
            new User({
                'id': 91,
                'user': 'dadanet2i',
                'name': 'Darryl Adanet',
                'password': 'df2e9d8df7a6947e327641574b027c66',
                'role': 0
            }),
            new User({
                'id': 92,
                'user': 'jbailiss2j',
                'name': 'Jacinthe Bailiss',
                'password': '2a7ee7b5e34c3b2056f1fb4708c4883f',
                'role': 0
            }),
            new User({
                'id': 93,
                'user': 'cvearncomb2k',
                'name': 'Cahra Vearncomb',
                'password': '4185c895ad158b7b2e541afa7ee5e19c',
                'role': 0
            }),
            new User({
                'id': 94,
                'user': 'cpercy2l',
                'name': 'Chris Percy',
                'password': '6efacdc26d0104699404be2231e9af64',
                'role': 1
            }),
            new User({
                'id': 95,
                'user': 'ddraye2m',
                'name': 'Demetra Draye',
                'password': '21bcbe91a673a8289183c3b84c876b7f',
                'role': 0
            }),
            new User({
                'id': 96,
                'user': 'dmclugaish2n',
                'name': 'Dmitri McLugaish',
                'password': '9c31514c556cd0ae71a217f621bb379b',
                'role': 0
            }),
            new User({
                'id': 97,
                'user': 'mhonsch2o',
                'name': 'Maison Honsch',
                'password': '806d35e5db387d22938f6c85be86c0ea',
                'role': 0
            }),
            new User({
                'id': 98,
                'user': 'lslimm2p',
                'name': 'Laurie Slimm',
                'password': '54be61fca2edcad967c604ead91fca86',
                'role': 1
            }),
            new User({
                'id': 99,
                'user': 'user',
                'name': 'Gretal Baudon',
                'password': 'ee11cbb19052e40b07aac0ca060c23ee',
                'role': 0
            }),
            new User({
                'id': 100,
                'user': 'admin',
                'name': 'Administrador',
                'password': '21232f297a57a5a743894a0e4a801fc3',
                'role': 1
            })];
    }
}
