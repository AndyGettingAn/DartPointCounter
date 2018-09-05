package com.example.cip.myapplication;

public class FinishValues {

    public FinishValues() {
    }

    public static String[] getFinish(int points){
        return calculateFinish(points);
    }

    // für doppelten Auswurf
    private static String [] calculateFinish(int points) {
        String noFinishMessage = "Kein Auswurf möglich";
        switch (points) {
            case 170:
                return new String[]{"T20", "T20", "D25"};
            case 167:
                return new String[]{"T20", "T19", "D25"};
            case 164:
                return new String[]{"T20", "T18", "D25"};
            case 161:
                return new String[]{"T20", "T17", "D25"};
            case 160:
                return new String[]{"T20", "T20", "D20"};
            case 158:
                return new String[]{"T20", "T20", "D19"};
            case 157:
                return new String[]{"T20", "T19", "D20"};
            case 156:
                return new String[]{"T20", "T20", "D12"};
            case 155:
                return new String[]{"T20", "T19", "D19"};
            case 154:
                return new String[]{"T20", "T18", "D20"};
            case 153:
                return new String[]{"T20", "T19", "D18"};
            case 152:
                return new String[]{"T20", "T20", "D16"};
            case 151:
                return new String[]{"T20", "T17", "D20"};
            case 150:
                return new String[]{"T20", "T18", "D18"};
            case 149:
                return new String[]{"T20", "T19", "D16"};
            case 148:
                return new String[]{"T20", "T20", "D14"};
            case 147:
                return new String[]{"T20", "T17", "D18"};
            case 146:
                return new String[]{"T20", "T18", "D16"};
            case 145:
                return new String[]{"T20", "T19", "D14"};
            case 144:
                return new String[]{"T20", "T20", "D12"};
            case 143:
                return new String[]{"T20", "T17", "D16"};
            case 142:
                return new String[]{"T20", "T14", "D20"};
            case 141:
                return new String[]{"T20", "T19", "D12"};
            case 140:
                return new String[]{"T20", "T20", "D10"};
            case 139:
                return new String[]{"T20", "T13", "D20"};
            case 138:
                return new String[]{"T20", "T18", "D12"};
            case 137:
                return new String[]{"T20", "T19", "D10"};
            case 136:
                return new String[]{"T20", "T20", "D8"};
            case 135:
                return new String[]{"T20", "T17", "D12"};
            case 134:
                return new String[]{"T20", "T16", "D13"};
            case 133:
                return new String[]{"T20", "T19", "D8"};
            case 132:
                return new String[]{"T20", "T16", "D12"};
            case 131:
                return new String[]{"T19", "T14", "D16"};
            case 130:
                return new String[]{"T20", "T20", "D5"};
            case 129:
                return new String[]{"T19", "T16", "D12"};
            case 128:
                return new String[]{"T18", "T14", "D16"};
            case 127:
                return new String[]{"T20", "T17", "D16"};
            case 126:
                return new String[]{"T19", "T19", "D6"};
            case 125:
                return new String[]{"T18", "T19", "D7"};
            case 124:
                return new String[]{"T20", "T14", "D11"};
            case 123:
                return new String[]{"T19", "T16", "D9"};
            case 122:
                return new String[]{"T18", "T18", "D7"};
            case 121:
                return new String[]{"T20", "T11", "D14"};
            case 120:
                return new String[]{"T20", "S20", "D20"};
            case 119:
                return new String[]{"T19", "T12", "D13"};
            case 118:
                return new String[]{"T20", "S18", "D20"};
            case 117:
                return new String[]{"T19", "S20", "D20"};
            case 116:
                return new String[]{"T19", "S19", "D20"};
            case 115:
                return new String[]{"T20", "S15", "D20"};
            case 114:
                return new String[]{"T19", "S17", "D20"};
            case 113:
                return new String[]{"T19", "S16", "D20"};
            case 112:
                return new String[]{"T20", "T12", "D8"};
            case 111:
                return new String[]{"T19", "S14", "D20"};
            case 110:
                return new String[]{"T20", "T10", "D10"};
            case 109:
                return new String[]{"T20", "S9", "D20"};
            case 108:
                return new String[]{"T20", "S16", "D16"};
            case 107:
                return new String[]{"T19", "T10", "D10"};
            case 106:
                return new String[]{"T19", "T10", "D8"};
            case 105:
                return new String[]{"T20", "S13", "D16"};
            case 104:
                return new String[]{"T19", "S15", "D16"};
            case 103:
                return new String[]{"T19", "S6", "D20"};
            case 102:
                return new String[]{"T20", "S10", "D16"};
            case 101:
                return new String[]{"T20", "S9", "D16"};
            case 100:
                return new String[]{"T20", "D20"};
            case 99:
                return new String[]{"T19", "S10", "D16"};
            case 98:
                return new String[]{"T20", "D19"};
            case 97:
                return new String[]{"T19", "D20"};
            case 96:
                return new String[]{"T20", "D18"};
            case 95:
                return new String[]{"T19", "D19"};
            case 94:
                return new String[]{"T18", "D20"};
            case 93:
                return new String[]{"T19", "D18"};
            case 92:
                return new String[]{"T20", "D16"};
            case 91:
                return new String[]{"T17", "D20"};
            case 90:
                return new String[]{"T20", "D15"};
            case 89:
                return new String[]{"T19", "D16"};
            case 88:
                return new String[]{"T20", "D14"};
            case 87:
                return new String[]{"T17", "D18"};
            case 86:
                return new String[]{"T18", "D16"};
            case 85:
                return new String[]{"T15", "D20"};
            case 84:
                return new String[]{"T20", "D12"};
            case 83:
                return new String[]{"T17", "D16"};
            case 82:
                return new String[]{"D25", "D16"};
            case 81:
                return new String[]{"T19", "D12"};
            case 80:
                return new String[]{"T20", "D10"};
            case 79:
                return new String[]{"T19", "D11"};
            case 78:
                return new String[]{"T18", "D12"};
            case 77:
                return new String[]{"T19", "D10"};
            case 76:
                return new String[]{"T20", "D8"};
            case 75:
                return new String[]{"T17", "D12"};
            case 74:
                return new String[]{"T14", "D16"};
            case 73:
                return new String[]{"T19", "D8"};
            case 72:
                return new String[]{"T16", "D12"};
            case 71:
                return new String[]{"T13", "D16"};
            case 70:
                return new String[]{"T18", "D8"};
            case 69:
                return new String[]{"T19", "D6"};
            case 68:
                return new String[]{"T16", "D10"};
            case 67:
                return new String[]{"T9", "D20"};
            case 66:
                return new String[]{"T10", "D18"};
            case 65:
                return new String[]{"T11", "D16"};
            case 64:
                return new String[]{"T16", "D8"};
            case 63:
                return new String[]{"T13", "D12"};
            case 62:
                return new String[]{"T10", "D16"};
            case 61:
                return new String[]{"T15", "D8"};
            case 60:
                return new String[]{"S20", "D20"};
            case 59:
                return new String[]{"S19", "D20"};
            case 58:
                return new String[]{"S18", "D20"};
            case 57:
                return new String[]{"S17", "D20"};
            case 56:
                return new String[]{"S16", "D20"};
            case 55:
                return new String[]{"S15", "D20"};
            case 54:
                return new String[]{"S14", "D20"};
            case 53:
                return new String[]{"S13", "D20"};
            case 52:
                return new String[]{"S12", "D20"};
            case 51:
                return new String[]{"S11", "D20"};
            case 50:
                return new String[]{"S10", "D20"};
            case 49:
                return new String[]{"S9", "D20"};
            case 48:
                return new String[]{"S16", "D16"};
            case 47:
                return new String[]{"S7", "D20"};
            case 46:
                return new String[]{"S6", "D20"};
            case 45:
                return new String[]{"S13", "D16"};
            case 44:
                return new String[]{"S12", "D16"};
            case 43:
                return new String[]{"S3", "D20"};
            case 42:
                return new String[]{"S10", "D16"};
            case 41:
                return new String[]{"S9", "D16"};
            case 40:
                return new String[]{"D20"};
            case 39:
                return new String[]{"S7", "D16"};
            case 38:
                return new String[]{"D19"};
            case 37:
                return new String[]{"S5", "D16"};
            case 36:
                return new String[]{"D18"};
            case 35:
                return new String[]{"S3", "D16"};
            case 34:
                return new String[]{"D17"};
            case 33:
                return new String[]{"S17", "D8"};
            case 32:
                return new String[]{"D16"};
            case 31:
                return new String[]{"S15", "D8"};
            case 30:
                return new String[]{"D15"};
            case 29:
                return new String[]{"S13", "D8"};
            case 28:
                return new String[]{"D14"};
            case 27:
                return new String[]{"S11", "D8"};
            case 26:
                return new String[]{"D13"};
            case 25:
                return new String[]{"S9", "D8"};
            case 24:
                return new String[]{"D12"};
            case 23:
                return new String[]{"S7", "D8"};
            case 22:
                return new String[]{"D11"};
            case 21:
                return new String[]{"S5", "D8"};
            case 20:
                return new String[]{"D10"};
            case 19:
                return new String[]{"S3", "D8"};
            case 18:
                return new String[]{"D9"};
            case 17:
                return new String[]{"S1", "D8"};
            case 16:
                return new String[]{"D8"};
            case 15:
                return new String[]{"S5", "D4"};
            case 14:
                return new String[]{"D7"};
            case 13:
                return new String[]{"S5", "D4"};
            case 12:
                return new String[]{"D6"};
            case 11:
                return new String[]{"S3", "D4"};
            case 10:
                return new String[]{"D5"};
            case 9:
                return new String[]{"S1", "D4"};
            case 8:
                return new String[]{"D4"};
            case 7:
                return new String[]{"S3", "D2"};
            case 6:
                return new String[]{"D3"};
            case 5:
                return new String[]{"S1", "D2"};
            case 4:
                return new String[]{"D2"};
            case 3:
                return new String[]{"S1", "D1"};
            case 2:
                return new String[]{"D1"};
            default:
                return new String[]{""};
        }
    }
}
